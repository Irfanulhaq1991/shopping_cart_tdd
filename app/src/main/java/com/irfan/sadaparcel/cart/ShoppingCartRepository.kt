package com.irfan.sadaparcel.cart

import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

class ShoppingCartRepository {
    fun fetchCartItems(): UiState.Success {
      return UiState.Success(message = "No data")
    }

    fun addItemToShoppingCart(inventoryItemWithQuantity: InventoryItemWithQuantity) {
        TODO("Not yet implemented")
    }
}
