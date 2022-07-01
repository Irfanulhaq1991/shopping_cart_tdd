package com.irfan.sadaparcel.cart

import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

interface ShoppingCartService {

   suspend fun fetchCartItems():List<InventoryItemWithQuantity>

   suspend fun addItemToShoppingCart(inventoryItemWithQuantity: InventoryItemWithQuantity):Boolean

}
