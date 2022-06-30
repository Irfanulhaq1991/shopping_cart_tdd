package com.irfan.sadaparcel.cart

import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ShoppingCartDbServiceShould{
    @Test
    fun returnNoCartItems(){
        val cartDbService = ShoppingCartDbService()
        assertThat(cartDbService.fetchCartItems()).isEqualTo(emptyList<InventoryItemWithQuantity>())
    }
}