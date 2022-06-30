package com.irfan.sadaparcel.inventory

import com.irfan.sadaparcel.InstantTaskExecutorExtension
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

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
        coVerify { inventoryRepo.fetchInventoryItems() }
    }
}