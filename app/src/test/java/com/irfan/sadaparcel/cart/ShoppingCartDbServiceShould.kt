package com.irfan.sadaparcel.cart

import android.database.sqlite.SQLiteException
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

class ShoppingCartDbServiceShould : ShoppingCartServiceContractTest() {
    override fun withData(data: List<InventoryItemWithQuantity>, limit: Int): ShoppingCartService {
       return ShoppingCartDbService(createApiWithData(data),limit)
    }

    override fun withException(): ShoppingCartService {
        return ShoppingCartDbService(createApiWithExceptionThrowing())
    }






    //  Api Fakes provider
    private fun createApiWithData(data: List<InventoryItemWithQuantity>):ShoppingCartDatabaseApi{
       return object : ShoppingCartDatabaseApi {
            val fakeDb = mutableListOf<InventoryItemWithQuantity>().apply { addAll(data) }
            override fun getAll(): List<InventoryItemWithQuantity> {
                return fakeDb
            }

            override fun add(itemWithQuantity: InventoryItemWithQuantity): Int {
                fakeDb.add(itemWithQuantity)
                return fakeDb.indexOf(itemWithQuantity)
            }
        }
    }

    private fun createApiWithExceptionThrowing():ShoppingCartDatabaseApi {
        return object : ShoppingCartDatabaseApi {
            override fun getAll(): List<InventoryItemWithQuantity> {
                throw SQLiteException()
            }

            override fun add(itemWithQuantity: InventoryItemWithQuantity): Int {
                throw SQLiteException()
            }

        }
    }

}