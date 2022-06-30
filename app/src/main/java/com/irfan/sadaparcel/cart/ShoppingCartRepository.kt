package com.irfan.sadaparcel.cart

import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

class ShoppingCartRepository(private val dbService: DbService) {

    fun fetchCartItems(): UiState {
        val cartItems = dbService.fetchCartItems()
        if(cartItems.isEmpty())
            return UiState.Success(message = "No Data")
        else
            return UiState.Success(cartItems)
    }

    fun addItemToShoppingCart(inventoryItemWithQuantity: InventoryItemWithQuantity) {
        TODO("Not yet implemented")
    }


}
