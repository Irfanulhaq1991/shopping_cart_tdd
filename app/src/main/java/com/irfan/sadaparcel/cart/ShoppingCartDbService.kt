package com.irfan.sadaparcel.cart

import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

class ShoppingCartDbService(private val dbApiShoppingCart:ShoppingCartDatabaseApi,private val limit:Int = 50): ShoppingCartService {


    override fun fetchCartItems():List<InventoryItemWithQuantity> {
        val cartItems = dbApiShoppingCart.getAll()
        return cartItems
    }

    override fun addItemToShoppingCart(inventoryItemWithQuantity: InventoryItemWithQuantity):Boolean {
      val cartItemCount = dbApiShoppingCart.getAll().size
      if(cartItemCount == limit) return false
       val itemDbId =  dbApiShoppingCart.add(inventoryItemWithQuantity)
       return itemDbId  >= 0
    }

}
