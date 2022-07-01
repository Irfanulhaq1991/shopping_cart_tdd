package com.irfan.sadaparcel.cart

import android.database.sqlite.SQLiteException
import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.DummyDataProvider
import com.irfan.sadaparcel.inventory.AppException
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ShoppingCartDbServiceShould {

    private val cartItems = DummyDataProvider.data


    @Test
    fun returnNoCartItems() {
        val cartDbService = withData(emptyList())
        assertThat(cartDbService.fetchCartItems()).isEqualTo(emptyList<InventoryItemWithQuantity>())
    }

    @Test
    fun returnManyCartItems() {
        val cartDbService = withData(cartItems)
        assertThat(cartDbService.fetchCartItems()).isEqualTo(cartItems)
    }

   @Test
   fun addItemSuccessfully(){
       val cartDbService = withData(emptyList())
       val result = cartDbService.addItemToShoppingCart(cartItems[0])
       assertThat(result).isTrue()
   }

    @Test
    fun notAddItemSuccessfully(){
        val cartDbService = withData(emptyList(),0)
        val result = cartDbService.addItemToShoppingCart(cartItems[0])
        assertThat(result).isFalse()
    }

   @Test
   fun throwExceptionWhenFetchingItems(){
       val cartDbService = withException()
       assertThrows<AppException> {
           cartDbService.fetchCartItems()
       }
   }
    @Test
    fun throwExceptionWhenAddItem(){
        val cartDbService = withException()
        assertThrows<AppException> {
            cartDbService.fetchCartItems()
        }
    }


    fun withData(cartItems: List<InventoryItemWithQuantity>, limit: Int = 50): ShoppingCartService {
        return ShoppingCartDbService(object : ShoppingCartDatabaseApi {
            val fakeDb = mutableListOf<InventoryItemWithQuantity>().apply { addAll(cartItems) }
            override fun getAll(): List<InventoryItemWithQuantity> {
                return fakeDb
            }

            override fun add(itemWithQuantity: InventoryItemWithQuantity): Int {
                fakeDb.add(itemWithQuantity)
                return fakeDb.indexOf(itemWithQuantity)
            }
        },limit)
    }





    fun withException(): ShoppingCartService {
        return ShoppingCartDbService(object : ShoppingCartDatabaseApi {
            override fun getAll(): List<InventoryItemWithQuantity> {
                throw SQLiteException()
            }

            override fun add(itemWithQuantity: InventoryItemWithQuantity): Int {
               throw SQLiteException()
            }

        })
    }


}


