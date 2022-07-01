package com.irfan.sadaparcel.cart

import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.inventory.AppException
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

class ShoppingCartRepository(private val shoppingCartDbService: ShoppingCartDbService) {

    fun fetchCartItems(): UiState {
        return try {
            val cartItems = shoppingCartDbService.fetchCartItems()
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
            val isAdditionSuccessful  = shoppingCartDbService.addItemToShoppingCart(inventoryItemWithQuantity)
            if(isAdditionSuccessful)
                UiState.Success(message = "Item Added to Shopping Cart")
            else
                UiState.Error("failed to add")
        }catch (e:AppException){
            UiState.Error(e.message)
        }
    }


}
