package com.irfan.sadaparcel.cart

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.InstantTaskExecutorExtension
import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.inventory.InventoryItem
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity
import com.irfan.sadaparcel.inventory.viewModelFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class ShoppingCartShould {
    private lateinit var uiController: ShoppingCartSpyUiController

    @BeforeEach
    fun setup() {
        val cartViewModel = ShoppingCartViewModel()
        uiController = ShoppingCartSpyUiController().apply { viewModel = cartViewModel }
        uiController.onCreate()
    }

    @Test
    fun fetchCartItems() {
        val shoppingCartItems =   listOf(
            InventoryItemWithQuantity(InventoryItem("1","item1","Description",2.1),1),
            InventoryItemWithQuantity(InventoryItem("1","item1","Description",2.1),1),
        )
        val expected = listOf(UiState.ShowLoading, UiState.Success(shoppingCartItems), UiState.HideLoading)
        uiController.fetchCartItems()

        val result = uiController.uiState
        assertThat(result).isEqualTo(expected)
    }
    @Test
    fun addItemToCart(){
        val shoppingCartItems =   listOf(
            InventoryItemWithQuantity(InventoryItem("1","item1","Description",2.1),1),
        )

        val expected = listOf(
            UiState.Success(message = "Added to Shopping Cart"),
            UiState.ShowLoading, UiState.Success(shoppingCartItems), UiState.HideLoading
        )

        uiController.addItemToShoppingCart(shoppingCartItems[0])
        uiController.fetchCartItems()

        val result = uiController.uiState
        assertThat(result).isEqualTo(expected)
    }

}

class ShoppingCartSpyUiController : LifecycleOwner {
    lateinit var viewModel: ShoppingCartViewModel
    val uiState = mutableListOf<UiState>()

    private val registry: LifecycleRegistry by lazy { LifecycleRegistry(this) }
    override fun getLifecycle() = registry

    fun onCreate() {
        registry.currentState = Lifecycle.State.STARTED
        viewModel.shoppingCartLiveData.observe(this, {
            uiState.add(it)
        })
    }

    fun fetchCartItems() {
        viewModel.fetchCartItems()
    }

    fun addItemToShoppingCart(inventoryItemWithQuantity: InventoryItemWithQuantity) {
            viewModel.addItemToShoppingCart(inventoryItemWithQuantity)
    }


}
