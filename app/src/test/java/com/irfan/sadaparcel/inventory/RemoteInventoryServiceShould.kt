package com.irfan.sadaparcel.inventory

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class RemoteInventoryServiceShould {

    @Test
    fun returnNoInventoryItems(){
        val remoteInventoryService  = RemoteInventoryService()
        assertThat(remoteInventoryService.getInventoryItems()).isEqualTo(emptyList<String>())
    }
    // I may convert this class contract tests to accommodate test for other inventory services like caches, database
}