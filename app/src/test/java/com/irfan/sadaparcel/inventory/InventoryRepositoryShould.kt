package com.irfan.sadaparcel.inventory

import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.UiStates
import org.junit.jupiter.api.Test

class InventoryRepositoryShould {
    @Test
    fun returnNoDataSuccessState(){
        val expected = UiStates.Success("no data", emptyList<String>())
        val result =  remoteService.getInventoryItems()
        
        assertThat(result).isEqualTo(expected)
    }

}