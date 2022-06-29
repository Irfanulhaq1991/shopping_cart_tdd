package com.irfan.sadaparcel.inventory

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class RemoteInventoryServiceShould {
    private val remoteInventoryService  = RemoteInventoryService()

    @Test
    fun returnNoInventoryItems(){
        assertThat(remoteInventoryService.getInventoryItems()).isEqualTo(emptyList<String>())
    }
   @Test
   fun returnOneInventoryItems(){
       assertThat(remoteInventoryService.getInventoryItems()).isEqualTo(listOf("Item1"))

   }

}