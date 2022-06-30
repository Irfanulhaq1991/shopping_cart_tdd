package com.irfan.sadaparcel.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

class ShoppingCartViewModel {
    private val _shoppingCartLiveData = MutableLiveData<UiState>()
    val shoppingCartLiveData:LiveData<UiState> = _shoppingCartLiveData

    fun fetchCartItems() {
        TODO("Not yet implemented")
    }

    fun addItemToShoppingCart(inventoryItemWithQuantity: InventoryItemWithQuantity) {
        TODO("Not yet implemented")
    }

}
