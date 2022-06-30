package com.irfan.sadaparcel.inventory

import android.content.Context
import com.irfan.sadaparcel.UiStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InventoryRepository(private val inventoryService: InventoryService) {
    suspend fun fetchInventoryItems(): UiStates {
        return withContext(Dispatchers.IO) {
            try {
                val inventoryItems = inventoryService.getInventoryItems()
                if (inventoryItems.isEmpty())
                    UiStates.Success(inventoryItems, "no data")
                else
                    UiStates.Success(inventoryItems)
            } catch (e: AppException) {
                UiStates.Error(e.message)
            }
        }
    }

}
