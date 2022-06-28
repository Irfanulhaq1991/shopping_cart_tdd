package com.irfan.sadaparcel.inventory

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.UiStates
import org.junit.jupiter.api.Test

// Acceptance Test
class InventoryShould {
    val inventorySpy = InventorySpyUiController().apply { viewModel = InventoryViewModel() }

    @Test
    fun loadInventoryItems() {
        //Given
        val expected = listOf("ShowLoading", "Success", "HidLoading")
        // when
        inventorySpy.fetchItems()

        //then
        val result = inventorySpy.uiStates
        assertThat(result).isEqualTo(expected)
    }
}

//https://blog.cleancoder.com/uncle-bob/2014/05/14/TheLittleMocker.html
class InventorySpyUiController():LifecycleOwner {
    val uiStates = mutableListOf<UiStates>()
    lateinit var viewModel: InventoryViewModel

    private val registry:LifecycleRegistry by lazy { LifecycleRegistry(this)}
    override fun getLifecycle() = registry

    fun onCreate(){
        registry.currentState = Lifecycle.State.STARTED
        viewModel.inventoryLiveData.observe(this,{
            uiStates.add(it)
        })
    }


    fun fetchItems() {
        viewModel.fetchInventory()
    }
}
