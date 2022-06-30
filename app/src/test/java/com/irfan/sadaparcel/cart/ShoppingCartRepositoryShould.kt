package com.irfan.sadaparcel.cart

import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.InstantTaskExecutorExtension
import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.inventory.InventoryItem
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ShoppingCartRepositoryShould {

    @RelaxedMockK
    private lateinit var dbService: DbService
    private lateinit var cartRepo: ShoppingCartRepository

    private val cartItems = listOf(
        InventoryItemWithQuantity(
            InventoryItem(
                "1", "item1",
                "Description",
                2.1
            ), 1
        ),
        InventoryItemWithQuantity(
            InventoryItem(
                "1", "item1",
                "Description",
                2.1
            ), 1
        ),
        InventoryItemWithQuantity(
            InventoryItem(
                "1", "item1",
                "Description",
                2.1
            ), 1
        )
    )

    @BeforeEach
    fun setup() {
        cartRepo = ShoppingCartRepository(dbService)
    }

    @Test
    fun returnSuccessStateWithNoDataMessage() {
        every { dbService.fetchCartItems() } answers { emptyList() }
        val result = cartRepo.fetchCartItems()
        assertThat(result).isEqualTo(UiState.Success(message = "No Data"))
    }

    @Test
    fun returnSuccessStateWithOneCartItem() {
        every { dbService.fetchCartItems() } answers { listOf(cartItems[0]) }
        val result = cartRepo.fetchCartItems()
        assertThat(result).isEqualTo(UiState.Success(listOf(cartItems[0])))
    }


}