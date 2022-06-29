package com.irfan.sadaparcel.inventory

import android.widget.RemoteViewsService
import com.irfan.sadaparcel.UiStates

class InventoryRepository(private val inventoryService: InventoryService) {
    fun fetchInventoryItems(): UiStates.Success {
        val inventoryItems = inventoryService.getInventoryItems()
       return if(inventoryItems.isEmpty())
            UiStates.Success(inventoryItems, "no data")
        else
            UiStates.Success(inventoryItems)
    }

}
