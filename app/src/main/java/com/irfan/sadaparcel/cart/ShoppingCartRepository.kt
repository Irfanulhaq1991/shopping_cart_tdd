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

    fun addItemToShoppingCart(inventoryItemWithQuantity: InventoryItemWithQuantity): UiState {
        return try {
            val isAdditionSuccessful  = dbService.addItemToShoppingCart(inventoryItemWithQuantity)
            if(isAdditionSuccessful)
                UiState.Success(message = "Added")
            else
                UiState.Error("failed to add")
        }catch (e:AppException){
            UiState.Error(e.message)
        }
    }


}
