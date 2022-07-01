package com.irfan.sadaparcel.inventory

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.DummyDataProvider
import com.irfan.sadaparcel.InstantTaskExecutorExtension
import com.irfan.sadaparcel.UiState
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

// Acceptance Test
@ExtendWith(InstantTaskExecutorExtension::class)
class InventoryShould {
    private val inventoryItems =  DummyDataProvider.data
    private lateinit var inventorySpy:InventorySpyUiController
    @BeforeEach
    fun setup(){
        val inventoryService = InventoryRemoteService(FakeInventoryRemoteApiWithData())
        val inventoryRepo = InventoryRepository(inventoryService)
        val inventoryViewModel = InventoryViewModel(inventoryRepo)
        inventorySpy = InventorySpyUiController().apply { viewModel = inventoryViewModel }
        inventorySpy.onCreate()
    }

    @Test
    fun loadInventoryItems() {
        //Given
        val expected = listOf(UiState.ShowLoading, UiState.Success(inventoryItems), UiState.HideLoading)
        // When
        inventorySpy.fetchItems()

        //Then
        val result = inventorySpy.uiStates
        assertThat(result).isEqualTo(expected)
    }
}

//https://blog.cleancoder.com/uncle-bob/2014/05/14/TheLittleMocker.html
class InventorySpyUiController:LifecycleOwner {
    val uiStates = mutableListOf<UiState>()
    lateinit var viewModel: InventoryViewModel
    private val countDownLatch: CountDownLatch = CountDownLatch(1)

    private val registry:LifecycleRegistry by lazy { LifecycleRegistry(this)}
    override fun getLifecycle() = registry

    fun onCreate(){
        registry.currentState = Lifecycle.State.STARTED
        viewModel.inventoryLiveData.observe(this,{
            uiStates.add(it)
            if(it == UiState.HideLoading)
                countDownLatch.countDown()
        })
    }


    fun fetchItems() {
        viewModel.fetchInventory()
        countDownLatch.await(300, TimeUnit.MILLISECONDS)

    }
}



