package com.irfan.sadaparcel.inventory

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.InstantTaskExecutorExtension
import com.irfan.sadaparcel.UiStates
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

// Acceptance Test
@ExtendWith(InstantTaskExecutorExtension::class)
class InventoryShould {
    private lateinit var inventorySpy:InventorySpyUiController
    @BeforeEach
    fun setup(){
        val inventoryItems =  listOf(
            InventoryItemWithQuantity(InventoryItem("1","item1","Description",2.1),1),
            InventoryItemWithQuantity(InventoryItem("1","item1","Description",2.1),1),
        )

        val inventoryService = InventoryRemoteService(FakeInventoryRemoteApiWithData(inventoryItems))
        val inventoryRepo = InventoryRepository(inventoryService)
        val inventoryViewModel = InventoryViewModel(inventoryRepo)
        inventorySpy = InventorySpyUiController().apply { viewModel = inventoryViewModel }
        inventorySpy.onCreate()
    }

    @Test
    fun loadInventoryItems() {
        //Given
        val expected = listOf(UiStates.Loading, UiStates.Success(), UiStates.HideLoading)
        // When
        inventorySpy.fetchItems()

        //Then
        val result = inventorySpy.uiStates
        assertThat(result).isEqualTo(expected)
    }
}

//https://blog.cleancoder.com/uncle-bob/2014/05/14/TheLittleMocker.html
class InventorySpyUiController():LifecycleOwner {
    val uiStates = mutableListOf<UiStates>()
    lateinit var viewModel: InventoryViewModel

    private val countDownLatch: CountDownLatch = CountDownLatch(1)


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
        countDownLatch.await(100, TimeUnit.MILLISECONDS)
    }
}
