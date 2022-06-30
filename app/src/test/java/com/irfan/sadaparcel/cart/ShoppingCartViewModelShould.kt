package com.irfan.sadaparcel.cart

import com.irfan.sadaparcel.InstantTaskExecutorExtension
import com.irfan.sadaparcel.inventory.InventoryItem
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class, MockKExtension::class)
class ShoppingCartViewModelShould {

    @RelaxedMockK
    private lateinit var cartRepo: ShoppingCartRepository
    private lateinit var viewModel: ShoppingCartViewModel
    @BeforeEach
    fun setup(){
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