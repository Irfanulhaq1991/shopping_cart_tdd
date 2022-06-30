package com.irfan.sadaparcel.cart

import androidx.lifecycle.ViewModel
import com.google.common.truth.Truth.assertThat
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
        verify { cartRepo.addItemToShoppingCart() }
    }
}