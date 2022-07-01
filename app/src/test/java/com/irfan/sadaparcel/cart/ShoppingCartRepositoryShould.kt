package com.irfan.sadaparcel.cart

import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.DummyDataProvider
import com.irfan.sadaparcel.UiState
import com.irfan.sadaparcel.inventory.AppException
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ShoppingCartRepositoryShould {

    @RelaxedMockK
    private lateinit var shoppingCartDbService: ShoppingCartDbService
    private lateinit var cartRepo: ShoppingCartRepository
    private val cartItems = DummyDataProvider.data

    @BeforeEach
    fun setup() {
        cartRepo = ShoppingCartRepository(shoppingCartDbService)
    }

    @Test
    fun returnSuccessStateWithNoDataMessage() {
        every { shoppingCartDbService.fetchCartItems() } answers { emptyList() }
        val result = cartRepo.fetchCartItems()
        assertThat(result).isEqualTo(UiState.Success(message = "No Data"))
    }

    @Test
    fun returnSuccessStateWithOneCartItem() {
        every { shoppingCartDbService.fetchCartItems() } answers { listOf(cartItems[0]) }
        val result = cartRepo.fetchCartItems()
        assertThat(result).isEqualTo(UiState.Success(listOf(cartItems[0])))
    }

    @Test
    fun returnSuccessStateWithManyCartItem() {
        every { shoppingCartDbService.fetchCartItems() } answers { cartItems }
        val result = cartRepo.fetchCartItems()
        assertThat(result).isEqualTo(UiState.Success(cartItems))
    }
    @Test
    fun returnErrorState(){
        every { shoppingCartDbService.fetchCartItems() } throws AppException()

        val result = cartRepo.fetchCartItems()
        assertThat(result).isEqualTo(UiState.Error("Error Occurred"))
    }

    // adding to card

    @Test
    fun returnSuccessStateAddedMessage(){
        every { shoppingCartDbService.addItemToShoppingCart(cartItems[0]) } returns true
        val result = cartRepo.addItemToShoppingCart(cartItems[0])
        assertThat(result).isEqualTo(UiState.Success(message = "Added"))
    }

    @Test
    fun returnErrorStateWithLimitExceedMessage(){
        every { shoppingCartDbService.addItemToShoppingCart(cartItems[0]) } throws  AppException("Cart limit exceeded")
        val result = cartRepo.addItemToShoppingCart(cartItems[0])
        assertThat(result).isEqualTo(UiState.Error(message = "Cart limit exceeded"))
    }

}