package com.irfan.sadaparcel.inventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfan.sadaparcel.UiState
import kotlinx.coroutines.launch


class InventoryViewModel(private val inventoryRepo:  InventoryRepository):ViewModel() {
    private val _inventoryLiveData = MutableLiveData<UiState>()
    val inventoryLiveData:LiveData<UiState> = _inventoryLiveData
    fun fetchInventory() {
        processFetchInventory()
    }
    private fun processFetchInventory(){
        viewModelScope.launch {
            _inventoryLiveData.value = UiState.ShowLoading
            _inventoryLiveData.value = inventoryRepo.fetchInventoryItems()
            _inventoryLiveData.value = UiState.HideLoading
        }
    }

}
