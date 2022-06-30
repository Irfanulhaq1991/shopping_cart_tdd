package com.irfan.sadaparcel

import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

sealed class UiState {
    object ShowLoading : UiState()
    object HideLoading : UiState()
    data class Error(val message: String? = "" ): UiState()

    //Not decided yet the type of data which success state  could carry to view
    data class Success(val data:List<InventoryItemWithQuantity> = emptyList() ,val message:String = ""): UiState()
}
