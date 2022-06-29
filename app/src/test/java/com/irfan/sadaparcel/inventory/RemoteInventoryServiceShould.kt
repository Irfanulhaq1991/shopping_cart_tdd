package com.irfan.sadaparcel.inventory

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

abstract class RemoteInventoryServiceShould {

    @Test
    fun returnNoInventoryItems(){
        val expected = emptyList<String>()
        val inventoryService  = withDataItems(expected)
        val result = inventoryService.getInventoryItems()
        assertThat(result).isEqualTo(expected)
    }
   @Test
   fun returnOneInventoryItems(){
       val expected = listOf("Item1")
       val inventoryService  = withDataItems(expected)
       val result = inventoryService.getInventoryItems()
       assertThat(result).isEqualTo(expected)
   }
    @Test
    fun returnManyInventoryItems(){
        val expected = listOf("Item1","item2")
        val inventoryService  = withDataItems(expected)
        val result = inventoryService.getInventoryItems()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun throwAppException(){
       val inventoryService = withAppException()
       assertThrows<AppException> {
           inventoryService.getInventoryItems()
       }
    }



   abstract fun withDataItems(data: List<String>): InventoryService
   abstract fun withAppException():InventoryService
}