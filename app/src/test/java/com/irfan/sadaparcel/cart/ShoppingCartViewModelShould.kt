package com.irfan.sadaparcel.cart

import com.irfan.sadaparcel.DummyDataProvider
import com.irfan.sadaparcel.InstantTaskExecutorExtension
import com.irfan.sadaparcel.inventory.InventoryItem
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class, MockKExtension::class)
@TestMethodOrder(MethodOrderer.MethodName::class)
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
        coVerify { cartRepo.fetchCartItems() }
    }

    @Test
    fun addItemToShoppingCart(){
    val inventoryItemWithQuantity = DummyDataProvider.data[0]
        viewModel.addItemToShoppingCart(inventoryItemWithQuantity)
        coVerify { cartRepo.addItemToShoppingCart(inventoryItemWithQuantity) }
    }

}