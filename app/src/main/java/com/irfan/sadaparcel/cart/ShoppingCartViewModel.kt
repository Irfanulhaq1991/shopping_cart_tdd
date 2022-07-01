package com.irfan.sadaparcel.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

class ShoppingCartViewModel(private val shoppingCartRepository: ShoppingCartRepository):ViewModel() {
    private val _shoppingCartLiveData = MutableLiveData<UiState>()
    val shoppingCartLiveData:LiveData<UiState> = _shoppingCartLiveData

    fun fetchCartItems() {
        _shoppingCartLiveData.value = UiState.ShowLoading
        _shoppingCartLiveData.value = shoppingCartRepository.fetchCartItems()
        _shoppingCartLiveData.value = UiState.HideLoading
    }

    fun addItemToShoppingCart(inventoryItemWithQuantity: InventoryItemWithQuantity) {
        _shoppingCartLiveData.value = UiState.ShowLoading
        _shoppingCartLiveData.value = shoppingCartRepository.addItemToShoppingCart(inventoryItemWithQuantity)
        _shoppingCartLiveData.value = UiState.HideLoading
    }

}
