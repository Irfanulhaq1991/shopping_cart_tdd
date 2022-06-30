package com.irfan.sadaparcel.cart

import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.UiState
import io.mockk.every
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ShoppingCartRepositoryShould{

    @Test
    fun returnSuccessStateWithNoDataMessage(){
        val result = ShoppingCartRepository().fetchCartItems()
        assertThat(result).isEqualTo(UiState.Success(message = "No data"))
    }


}