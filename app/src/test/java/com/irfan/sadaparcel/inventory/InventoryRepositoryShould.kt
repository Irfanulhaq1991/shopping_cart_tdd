package com.irfan.sadaparcel.inventory

import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.UiState
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

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
    fun returnNoDataSuccessState()= runTest{
        val expected = UiState.Success( emptyList<InventoryItemWithQuantity>(),"no data")
        coEvery { remoteInventoryService.getInventoryItems() } answers { emptyList()}

        val result =  inventoryRepository.fetchInventoryItems()
        assertThat(result).isEqualTo(expected)
    }
    @Test
    fun returnOneItemSuccessState()= runTest{
        //Given
        val inventoryItems =  listOf(
            InventoryItemWithQuantity(InventoryItem("1","item1","Description",2.1),1),
        )
        val expected  = UiState.Success(inventoryItems)
        coEvery { remoteInventoryService.getInventoryItems() } answers { inventoryItems}

        //When
        val result = inventoryRepository.fetchInventoryItems()
        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun returnManyItemSuccessState()= runTest{
        //Given
        val inventoryItems =  listOf(
            InventoryItemWithQuantity(InventoryItem("1","item1","Description",2.1),1),
            InventoryItemWithQuantity(InventoryItem("2","item2","Description2",2.2),2)
        )
        val expected  = UiState.Success(inventoryItems)
        coEvery { remoteInventoryService.getInventoryItems() } answers { inventoryItems}

        //When
        val result = inventoryRepository.fetchInventoryItems()
        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun returnErrorWithNoInternetMessage()= runTest{
        val expected  = UiState.Error("No Internet")
        coEvery { remoteInventoryService.getInventoryItems() } throws AppException("No Internet")

        val result = inventoryRepository.fetchInventoryItems()
        assertThat(result).isEqualTo(expected)
    }
    /*
         - I have custom exception to decouple the application from network, server, database and etc errors
         - More test could be added to validate and guard the application  network, server, database and etc errors
     */



}