package com.irfan.sadaparcel.cart

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.DummyDataProvider
import com.irfan.sadaparcel.FakeInMemoryShoppingCartDatabaseApi
import com.irfan.sadaparcel.InstantTaskExecutorExtension
import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.inventory.InventoryItem
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@ExtendWith(InstantTaskExecutorExtension::class)
class ShoppingCartShould {
    private lateinit var uiController: ShoppingCartSpyUiController

    @BeforeEach
    fun setup() {
        val dbService = ShoppingCartDbService(FakeInMemoryShoppingCartDatabaseApi())
        val cartRepository = ShoppingCartRepository(dbService)
        val cartViewModel = ShoppingCartViewModel(cartRepository)
        uiController = ShoppingCartSpyUiController().apply { viewModel = cartViewModel }
        uiController.onCreate()
    }

    @Test
    fun fetchCartItems() {

        val expected = listOf(UiState.ShowLoading, UiState.Success(DummyDataProvider.data), UiState.HideLoading)
        uiController.fetchCartItems()

        val result = uiController.uiState
        assertThat(result).isEqualTo(expected)
    }
    @Test
    fun addItemToCart(){
        val shoppingCartItems = DummyDataProvider.data[0]

        val expected = listOf(
            UiState.ShowLoading,
            UiState.Success(message = "Item Added to Shopping Cart"),
            UiState.HideLoading
        )

        uiController.addItemToShoppingCart(shoppingCartItems)

        val result = uiController.uiState
        assertThat(result).isEqualTo(expected)
    }

}

class ShoppingCartSpyUiController : LifecycleOwner {
    lateinit var viewModel: ShoppingCartViewModel
    val uiState = mutableListOf<UiState>()
    private val countDownLatch: CountDownLatch = CountDownLatch(1)


    private val registry: LifecycleRegistry by lazy { LifecycleRegistry(this) }
    override fun getLifecycle() = registry

    fun onCreate() {
        registry.currentState = Lifecycle.State.STARTED
        viewModel.shoppingCartLiveData.observe(this, {
            uiState.add(it)
            if(it == UiState.HideLoading)
                countDownLatch.countDown()
        })
    }

    fun fetchCartItems() {
        viewModel.fetchCartItems()
        countDownLatch.await(300, TimeUnit.MILLISECONDS)
    }

    fun addItemToShoppingCart(inventoryItemWithQuantity: InventoryItemWithQuantity) {
            viewModel.addItemToShoppingCart(inventoryItemWithQuantity)
    }

}

