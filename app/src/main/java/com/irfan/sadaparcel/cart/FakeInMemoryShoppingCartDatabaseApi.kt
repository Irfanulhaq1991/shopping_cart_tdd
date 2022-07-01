package com.irfan.sadaparcel.cart

import com.irfan.sadaparcel.DummyDataProvider
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

class FakeInMemoryShoppingCartDatabaseApi(private val items:List<InventoryItemWithQuantity> = emptyList()): ShoppingCartDatabaseApi {
    private val fakeDb = mutableListOf<InventoryItemWithQuantity>().apply { addAll(items) }
    override suspend fun getAll(): List<InventoryItemWithQuantity> {
        return fakeDb
    }

    override suspend fun add(itemWithQuantity: InventoryItemWithQuantity): Int {
        fakeDb.add(itemWithQuantity)
        return fakeDb.indexOf(itemWithQuantity)
    }
}