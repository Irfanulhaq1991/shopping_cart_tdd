package com.irfan.sadaparcel.cart

import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

interface ShoppingCartService {
    fun fetchCartItems():List<InventoryItemWithQuantity>

    fun addItemToShoppingCart(inventoryItemWithQuantity: InventoryItemWithQuantity):Boolean

}
