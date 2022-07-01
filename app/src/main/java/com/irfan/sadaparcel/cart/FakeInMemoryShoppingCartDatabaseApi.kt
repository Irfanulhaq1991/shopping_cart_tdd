package com.irfan.sadaparcel.cart

import com.irfan.sadaparcel.DummyDataProvider
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

class FakeInMemoryShoppingCartDatabaseApi: ShoppingCartDatabaseApi {
    private val fakeDb = mutableListOf<InventoryItemWithQuantity>().apply { addAll(DummyDataProvider.data) }
    override fun getAll(): List<InventoryItemWithQuantity> {
        return fakeDb
    }

    override fun add(itemWithQuantity: InventoryItemWithQuantity): Int {
        fakeDb.add(itemWithQuantity)
        return fakeDb.indexOf(itemWithQuantity)
    }
}