package com.irfan.sadaparcel.inventory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.irfan.sadaparcel.R
import com.irfan.sadaparcel.RemoteApiConfiguration
import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.cart.FakeInMemoryShoppingCartDatabaseApi
import com.irfan.sadaparcel.cart.ShoppingCartDbService
import com.irfan.sadaparcel.cart.ShoppingCartRepository
import com.irfan.sadaparcel.cart.ShoppingCartViewModel
import com.irfan.sadaparcel.viewModelFactory
import kotlinx.android.synthetic.main.fragment_inventory.*

class InventoryFragment : Fragment(), ItemLayoutManger,Observer<UiState> {

  private val adaptor:RcAdaptor<InventoryItemWithQuantity> by lazy {
       RcAdaptor<InventoryItemWithQuantity>(this).apply { bindRecyclerView(rc_item_list) }
   }


    private val viewModel: InventoryViewModel by lazy {
        //dependencies
      val remoteInventoryApi =  RemoteApiConfiguration.getRemoteApi(InventoryRemoteApi::class.java)
       val remoteInventoryService = InventoryRemoteService(remoteInventoryApi)
//      val remoteInventoryService = InventoryRemoteService(FakeInventoryRemoteApiWithData())
        val inventoryRepo = InventoryRepository(remoteInventoryService)

        ViewModelProvider(
            this, viewModelFactory { InventoryViewModel(inventoryRepo) }
        )[InventoryViewModel::class.java]

    }


    private val shoppingCartViewModel: ShoppingCartViewModel by lazy {
        //dependencies
        val shoppingCartDbService = ShoppingCartDbService(FakeInMemoryShoppingCartDatabaseApi.getInstance())
        val shoppingCartRepo = ShoppingCartRepository(shoppingCartDbService)

        ViewModelProvider(
            this, viewModelFactory { ShoppingCartViewModel(shoppingCartRepo) }
        )[ShoppingCartViewModel::class.java]
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
        requireActivity().title = "SadaParcel"
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
             shoppingCartViewModel.addItemToShoppingCart(inventoryItem)
        }
    }


    override fun onChanged(uiState: UiState?) {
        when(uiState){
            is UiState.ShowLoading ->{
                loader.visibility = View.VISIBLE
            }
            is UiState.HideLoading -> {
                loader.visibility = View.GONE
            }
            is UiState.Success -> { adaptor.setItems(uiState.data) }
            is UiState.Error -> { }
        }
    }
}

