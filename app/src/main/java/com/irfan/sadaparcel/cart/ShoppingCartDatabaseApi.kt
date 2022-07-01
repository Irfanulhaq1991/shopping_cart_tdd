package com.irfan.sadaparcel.cart

import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

// only two operation is added for now
interface ShoppingCartDatabaseApi {
   suspend fun getAll():List<InventoryItemWithQuantity>
   suspend fun add(itemWithQuantity: InventoryItemWithQuantity):Int // return id of the inserted element
}
