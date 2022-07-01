package com.irfan.sadaparcel.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.irfan.sadaparcel.R
import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity
import com.irfan.sadaparcel.inventory.ItemLayoutManger
import com.irfan.sadaparcel.inventory.RcAdaptor
import com.irfan.sadaparcel.viewModelFactory
import kotlinx.android.synthetic.main.fragment_inventory.*

class ShoppingCartFragment : Fragment(), ItemLayoutManger,Observer<UiState> {

   private val adaptor: RcAdaptor<InventoryItemWithQuantity> by lazy {
       RcAdaptor<InventoryItemWithQuantity>(this).apply { bindRecyclerView(rc_item_list) }
   }


    private val viewModel: ShoppingCartViewModel by lazy {
        //dependencies
       val shoppingCartDbService = ShoppingCartDbService(FakeInMemoryShoppingCartDatabaseApi.getInstance())
        val shoppingCartRepo = ShoppingCartRepository(shoppingCartDbService)

        ViewModelProvider(
            this, viewModelFactory { ShoppingCartViewModel(shoppingCartRepo) }
        )[ShoppingCartViewModel::class.java]
    }



    companion object {
        fun newInstance() = ShoppingCartFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Cart"
        viewModel.shoppingCartLiveData.observe(viewLifecycleOwner,this)
        viewModel.fetchCartItems()
    }
    override fun getLayoutId(position: Int): Int {
        return R.layout.cart_row
    }

    override fun bindView(view: View) {
        val inventoryItem = (view.tag as Pair<Int,InventoryItemWithQuantity>).second
        val nameTextView = view.findViewById<TextView>(R.id.item_name).apply { text = inventoryItem.item.name}
        val priceTextView = view.findViewById<TextView>(R.id.item_price).apply { text = inventoryItem.item.price.toString()}
        val quantityTextView = view.findViewById<TextView>(R.id.item_quantity).apply { text = inventoryItem.quantity.toString()}
        val itemAddBtn = view.findViewById<AppCompatImageButton>(R.id.item_add_btn)
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

