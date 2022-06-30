package com.irfan.sadaparcel.inventory

import androidx.lifecycle.*
import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.inventory.InventoryRepository
import kotlinx.coroutines.launch


class InventoryViewModel(private val inventoryRepo: InventoryRepository):ViewModel() {
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

inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(aClass: Class<T>):T = f() as T
    }