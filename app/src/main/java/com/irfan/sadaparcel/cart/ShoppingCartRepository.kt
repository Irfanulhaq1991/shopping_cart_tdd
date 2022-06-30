package com.irfan.sadaparcel.cart

import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.inventory.AppException
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

class ShoppingCartRepository(private val dbService: DbService) {

    fun fetchCartItems(): UiState {
        return try {
            val cartItems = dbService.fetchCartItems()
            if (cartItems.isEmpty())
                UiState.Success(message = "No Data")
            else
                UiState.Success(cartItems)
        }catch (e:AppException){
            UiState.Error("Error Occurred")
        }
    }

    fun addItemToShoppingCart(inventoryItemWithQuantity: InventoryItemWithQuantity): UiState.Success {
        return UiState.Success(message = "Added")
    }


}
