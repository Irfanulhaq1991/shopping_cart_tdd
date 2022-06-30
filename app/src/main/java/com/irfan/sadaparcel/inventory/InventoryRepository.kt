package com.irfan.sadaparcel.inventory

import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.inventory.AppException
import com.irfan.sadaparcel.inventory.InventoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InventoryRepository(private val inventoryService: InventoryService) {
    suspend fun fetchInventoryItems(): UiState {
        return withContext(Dispatchers.IO) {
            try {
                val inventoryItems = inventoryService.getInventoryItems()
                if (inventoryItems.isEmpty())
                    UiState.Success(inventoryItems, "no data")
                else
                    UiState.Success(inventoryItems)
            } catch (e: AppException) {
                UiState.Error(e.message)
            }
        }
    }

}
