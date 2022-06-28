package com.irfan.sadaparcel.inventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.sadaparcel.UiStates

class InventoryViewModel:ViewModel() {
    private val _inventoryLiveData = MutableLiveData<UiStates>()
    val inventoryLiveData:LiveData<UiStates> = _inventoryLiveData

    fun fetchInventory() {
        TODO("Not yet implemented")
    }

}
