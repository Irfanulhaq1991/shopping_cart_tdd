package com.irfan.sadaparcel.inventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.sadaparcel.UiStates


class InventoryViewModel(private val inventoryRepo:  InventoryRepository):ViewModel() {
    private val _inventoryLiveData = MutableLiveData<UiStates>()
    val inventoryLiveData:LiveData<UiStates> = _inventoryLiveData
    fun fetchInventory() {
        _inventoryLiveData.value = UiStates.Loading
        _inventoryLiveData.value = inventoryRepo.fetchInventoryItems()
        _inventoryLiveData.value = UiStates.HideLoading
    }

}
