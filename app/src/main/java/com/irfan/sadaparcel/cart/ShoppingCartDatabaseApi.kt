package com.irfan.sadaparcel.cart

import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

// only two operation is added for now
interface ShoppingCartDatabaseApi {
    fun getAll():List<InventoryItemWithQuantity>
    fun add(itemWithQuantity: InventoryItemWithQuantity):Int // return id of the inserted element
}
