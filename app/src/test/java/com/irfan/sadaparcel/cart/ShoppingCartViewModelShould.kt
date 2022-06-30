package com.irfan.sadaparcel.cart

import androidx.lifecycle.ViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.verify
import org.junit.jupiter.api.Test

class ShoppingCartViewModelShould {



    @Test
    fun fetchCartItems(){
        viewModel.fetchCartItems()
        verify {cartRepo.fetchCartItems()}
    }
}