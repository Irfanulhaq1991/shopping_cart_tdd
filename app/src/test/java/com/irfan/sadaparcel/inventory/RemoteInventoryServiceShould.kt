package com.irfan.sadaparcel.inventory

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class RemoteInventoryServiceShould {

    @Test
    fun returnNoInventoryItems(){
        assertThat(RemoteInventoryService().getInventoryItems()).isEqualTo(emptyList<String>())
    }
}