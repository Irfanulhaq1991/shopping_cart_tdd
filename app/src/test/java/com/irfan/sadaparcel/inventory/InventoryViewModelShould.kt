package com.irfan.sadaparcel.inventory

import io.mockk.verify
import org.junit.jupiter.api.Test

class InventoryViewModelShould {


    private val inventoryViewModel: InventoryViewModel = InventoryViewModel()
    @Test
    fun fetchInventoryItems(){
        inventoryViewModel.fetchInventory()
        verify { invetoryRepo.fetchInventoryItems() }
    }
}