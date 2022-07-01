package com.irfan.sadaparcel.cart

import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.DummyDataProvider
import com.irfan.sadaparcel.inventory.AppException
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

abstract class ShoppingCartServiceContractTest {

    private val cartItems = DummyDataProvider.data


    @Test
    fun returnNoCartItems() = runTest {
        val cartDbService = withData(emptyList())
        assertThat(cartDbService.fetchCartItems()).isEqualTo(emptyList<InventoryItemWithQuantity>())
    }

    @Test
    fun returnManyCartItems() = runTest {
        val cartDbService = withData(cartItems)
        assertThat(cartDbService.fetchCartItems()).isEqualTo(cartItems)
    }

    @Test
    fun addItemSuccessfully() = runTest {
        val cartDbService = withData(emptyList())
        val result = cartDbService.addItemToShoppingCart(cartItems[0])
        assertThat(result).isTrue()
    }

    @Test
    fun notAddItemSuccessfullyAndThrowException() = runTest {
        val cartDbService = withData(emptyList(), 0)
        assertThrows<AppException> {
            cartDbService.addItemToShoppingCart(cartItems[0])
        }
    }

    @Test
    fun throwExceptionWhenFetchingItems() = runTest {
        val cartDbService = withException()
        assertThrows<AppException> {
            cartDbService.fetchCartItems()
        }
    }

    @Test
    fun throwExceptionWhenAddItem() = runTest {
        val cartDbService = withException()
        assertThrows<AppException> {
            cartDbService.addItemToShoppingCart(cartItems[0])
        }
    }

    abstract fun withData(
        data: List<InventoryItemWithQuantity>,
        limit: Int = 50
    ): ShoppingCartService

    abstract fun withException(): ShoppingCartService
}


