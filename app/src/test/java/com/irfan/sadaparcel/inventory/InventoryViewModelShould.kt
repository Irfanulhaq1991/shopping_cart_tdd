package com.irfan.sadaparcel.inventory

import com.irfan.sadaparcel.InstantTaskExecutorExtension
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith

// Verify behaviors of the viewmodel
@ExtendWith(InstantTaskExecutorExtension::class, MockKExtension::class)
class InventoryViewModelShould {

    @RelaxedMockK
    private lateinit var inventoryRepo: InventoryRepository
    private lateinit var inventoryViewModel: InventoryViewModel

    @BeforeEach
    fun setup(){
        inventoryViewModel = InventoryViewModel(inventoryRepo)
    }

    @Test
    fun fetchInventoryItems(){
        inventoryViewModel.fetchInventory()
        verify { inventoryRepo.fetchInventoryItems() }
    }
}