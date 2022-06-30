package com.irfan.sadaparcel.cart

import com.irfan.sadaparcel.inventory.InventoryItem
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ShoppingCartViewModelShould {

    private lateinit var cartRepo: ShoppingCartRepository
    private lateinit var viewModel: ShoppingCartViewModel
    @BeforeEach
    fun setup(){
        cartRepo = ShoppingCartRepository()
        viewModel = ShoppingCartViewModel(cartRepo)
    }
    @Test
    fun fetchCartItems(){
        viewModel.fetchCartItems()
        verify {cartRepo.fetchCartItems()}
    }
    @Test
    fun addItemToShoppingCart(){
      val inventoryItemWithQuantity =  InventoryItemWithQuantity(InventoryItem("1","item1","Description",2.1),1)

        viewModel.addItemToShoppingCart(inventoryItemWithQuantity)
        verify { cartRepo.addItemToShoppingCart(inventoryItemWithQuantity) }
    }
}