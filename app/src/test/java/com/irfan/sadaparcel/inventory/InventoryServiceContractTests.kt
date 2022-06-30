package com.irfan.sadaparcel.inventory

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


abstract class InventoryServiceContractTests {

    @Test
    fun returnNoInventoryItems()= runTest{
        val expected = emptyList<InventoryItemWithQuantity>()
        val inventoryService  = withDataItems(expected)
        val result = inventoryService.getInventoryItems()
        assertThat(result).isEqualTo(expected)
    }
   @Test
   fun returnOneInventoryItems() = runTest{
       val expected = listOf(
           InventoryItemWithQuantity(InventoryItem("1","item1","Description",2.5),5)
       )
       val inventoryService  = withDataItems(expected)
       val result = inventoryService.getInventoryItems()
       assertThat(result).isEqualTo(expected)
   }
    @Test
    fun returnManyInventoryItems() = runTest{
        val expected = listOf(
            InventoryItemWithQuantity(InventoryItem("1","item1","Description",2.1),1),
            InventoryItemWithQuantity(InventoryItem("2","item2","Description2",2.2),2)
        )
        val inventoryService  = withDataItems(expected)
        val result = inventoryService.getInventoryItems()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun throwAppException()= runTest{
       val inventoryService = withAppException()
       assertThrows<AppException> {
           inventoryService.getInventoryItems()
       }
    }



   abstract fun withDataItems(inventoryItems: List<InventoryItemWithQuantity>): InventoryService
   abstract fun withAppException():InventoryService
}