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

    @MockK
    private lateinit var remoteInventoryService:InventoryService
    private lateinit var inventoryRepository: InventoryRepository
    @BeforeEach
    fun setup(){
         inventoryRepository = InventoryRepository(remoteInventoryService)
    }

    @Test
    fun returnNoDataSuccessState(){
        val expected = UiStates.Success( emptyList<String>(),"no data")
        every { remoteInventoryService.getInventoryItems() } answers { emptyList()}

        val result =  inventoryRepository.fetchInventoryItems()
        assertThat(result).isEqualTo(expected)
    }
    @Test
    fun returnOneItemSuccessState(){
        val expected  = UiStates.Success(listOf("Hello"))
        every { remoteInventoryService.getInventoryItems() } answers { listOf("Hello") }

        val result = inventoryRepository.fetchInventoryItems()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun returnManyItemSuccessState(){
        val expected  = UiStates.Success(listOf("Hello","Hi"))
        every { remoteInventoryService.getInventoryItems() } answers { listOf("Hello","Hi") }

        val result = inventoryRepository.fetchInventoryItems()
        assertThat(result).isEqualTo(expected)
    }

  
}