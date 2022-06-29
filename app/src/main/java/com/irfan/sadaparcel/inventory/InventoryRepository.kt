package com.irfan.sadaparcel.inventory

import android.widget.RemoteViewsService
import com.irfan.sadaparcel.UiStates

class InventoryRepository() {
    fun fetchInventoryItems(): UiStates.Success {
        return UiStates.Success( emptyList<String>(),"no data")
    }

}
