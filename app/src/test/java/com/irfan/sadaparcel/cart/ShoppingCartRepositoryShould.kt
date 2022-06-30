package com.irfan.sadaparcel.cart

import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.inventory.InventoryItem
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity
import io.mockk.every
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ShoppingCartRepositoryShould{

    @Test
    fun returnSuccessStateWithNoDataMessage(){
        val result = ShoppingCartRepository().fetchCartItems()
        assertThat(result).isEqualTo(UiState.Success(message = "No data"))
    }

    @Test
    fun returnSuccessStateWithOneCartItem(){
        val result = ShoppingCartRepository().fetchCartItems()
        assertThat(result).isEqualTo(UiState.Success(listOf(InventoryItemWithQuantity(InventoryItem("1","item1","Description",2.1),1))))
    }


}