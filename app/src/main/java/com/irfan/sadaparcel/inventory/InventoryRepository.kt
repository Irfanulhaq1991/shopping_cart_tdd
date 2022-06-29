package com.irfan.sadaparcel.inventory

import android.widget.RemoteViewsService
import com.irfan.sadaparcel.UiStates

class InventoryRepository(private val inventoryService: InventoryService) {
    fun fetchInventoryItems(): UiStates {
        return try {
            val inventoryItems = inventoryService.getInventoryItems()
            if (inventoryItems.isEmpty())
                UiStates.Success(inventoryItems, "no data")
            else
                UiStates.Success(inventoryItems)
        } catch (noInternetException: NoInternetException) {
             UiStates.NoInternetError
        }
    }

}
