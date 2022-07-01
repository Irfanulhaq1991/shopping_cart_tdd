package com.irfan.sadaparcel.inventory

import androidx.lifecycle.*
import com.irfan.sadaparcel.BaseViewModel
import com.irfan.sadaparcel.UiState
import kotlinx.coroutines.launch


class InventoryViewModel(private val inventoryRepo: InventoryRepository): BaseViewModel() {
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

