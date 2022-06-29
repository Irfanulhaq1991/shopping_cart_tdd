package com.irfan.sadaparcel.inventory

import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.UiStates
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InventoryRepositoryShould {
    private lateinit var inventoryRepository: InventoryRepository
    @BeforeEach
    fun setup(){
         inventoryRepository = InventoryRepository()
    }



    @Test
    fun returnNoDataSuccessState(){
        val expected = UiStates.Success("no data", emptyList<String>())
        val result =  inventoryRepository.fetchInventoryItems()

        assertThat(result).isEqualTo(expected)
    }

}