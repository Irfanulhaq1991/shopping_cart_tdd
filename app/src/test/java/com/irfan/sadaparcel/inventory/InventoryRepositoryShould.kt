package com.irfan.sadaparcel.inventory

import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.UiStates
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith

@ExtendWith(MockKExtension::class)
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
    // More triangulation here
}