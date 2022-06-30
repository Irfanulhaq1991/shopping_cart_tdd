package com.irfan.sadaparcel.inventory

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.irfan.sadaparcel.R
import com.irfan.sadaparcel.UiState
import kotlinx.android.synthetic.main.fragment_inventory.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class InventoryFragment : Fragment(), ItemLayoutManger,Observer<UiState> {

   val adaptor:RcAdaptor<InventoryItemWithQuantity> by lazy {
       RcAdaptor<InventoryItemWithQuantity>(this).apply { bindRecyclerView(rc_item_list) }
   }


    private val viewModel: InventoryViewModel by lazy {
        //dependencies
      // val remoteInventoryApi =  RemoteApiConfiguration.getRemoteApi(InventoryRemoteApi::class.java)
       val remoteInventoryService = InventoryRemoteService(FakeInventoryRemoteApiWithData())
        val inventoryRepo = InventoryRepository(remoteInventoryService)

        ViewModelProvider(
            this, viewModelFactory { InventoryViewModel(inventoryRepo) }
        )[InventoryViewModel::class.java]

    }



    companion object {
        fun newInstance() = InventoryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.inventoryLiveData.observe(viewLifecycleOwner,this)
        viewModel.fetchInventory()
    }
    override fun getLayoutId(position: Int): Int {
        return R.layout.inventory_grid_cell
    }

    override fun bindView(view: View) {
        val inventoryItem = (view.tag as Pair<Int,InventoryItemWithQuantity>).second
        val nameTextView = view.findViewById<TextView>(R.id.item_name).apply { text = inventoryItem.item.name}
        val priceTextView = view.findViewById<TextView>(R.id.item_price).apply { text = inventoryItem.item.price.toString()}
        val quantityTextView = view.findViewById<TextView>(R.id.item_quantity).apply { text = inventoryItem.quantity.toString()}
        val itemAddBtn = view.findViewById<AppCompatImageButton>(R.id.item_add_btn)
        itemAddBtn.setOnClickListener {
            val taggedView  = it.parent as ConstraintLayout
            val inventoryItem = (taggedView.tag as Pair<Int,InventoryItemWithQuantity>).second
            Toast.makeText(requireContext(),inventoryItem.item.name,Toast.LENGTH_SHORT).show()
        }
    }


    override fun onChanged(uiState: UiState?) {
        when(uiState){
            is UiState.ShowLoading ->{}
            is UiState.HideLoading -> {}
            is UiState.Success -> { adaptor.setItems(uiState.data) }
            else ->{}
        }
    }
}

class FakeInventoryRemoteApiWithData() : InventoryRemoteApi {

    private val inventoryItems =  listOf(
        InventoryItemWithQuantity(InventoryItem("1","item1","Description",2.1),1),
        InventoryItemWithQuantity(InventoryItem("2","item2","Description",2.2),1),
        InventoryItemWithQuantity(InventoryItem("3","item3","Description",2.4),1),
        InventoryItemWithQuantity(InventoryItem("4","item4","Description",2.3),1),
        InventoryItemWithQuantity(InventoryItem("5","item5","Description",2.3),1),
        InventoryItemWithQuantity(InventoryItem("6","item6","Description",2.3),1),
        InventoryItemWithQuantity(InventoryItem("7","item7","Description",2.3),1),
        InventoryItemWithQuantity(InventoryItem("8","item8","Description",2.3),1),
        InventoryItemWithQuantity(InventoryItem("9","item9","Description",2.3),1),
        InventoryItemWithQuantity(InventoryItem("10","item10","Description",2.3),1),
        InventoryItemWithQuantity(InventoryItem("11","item11","Description",2.3),1),
        InventoryItemWithQuantity(InventoryItem("12","item12","Description",2.3),1),
        InventoryItemWithQuantity(InventoryItem("13","item13","Description",2.3),1),
        InventoryItemWithQuantity(InventoryItem("14","item14","Description",2.3),1),
    )
    override suspend fun getInventoryItems(): Response<ResponseBody> {
        return Response.success(createResponseBody())
    }

    private fun createResponseBody(): ResponseBody {
        val typToken = object : TypeToken<List<InventoryItemWithQuantity>>() {}.type
        val jsonData = Gson().toJson(inventoryItems, typToken)
        val contentType = "application/json; charset=utf-8".toMediaType()
        return jsonData.toResponseBody(contentType)
    }
}