package com.irfan.sadaparcel.cart

import android.database.sqlite.SQLiteException
import com.irfan.sadaparcel.inventory.AppException
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

class ShoppingCartDbService(private val dbApiShoppingCart:ShoppingCartDatabaseApi,private val limit:Int = 50): ShoppingCartService {


    override fun fetchCartItems():List<InventoryItemWithQuantity> {
        try {
            return dbApiShoppingCart.getAll()
        }catch (e:SQLiteException){
            throw AppException("Error occurred while adding cartItems")
        }

    }

    override fun addItemToShoppingCart(inventoryItemWithQuantity: InventoryItemWithQuantity):Boolean {
        try {
            val cartItemCount = dbApiShoppingCart.getAll().size
            if (cartItemCount == limit) return false
            val itemDbId = dbApiShoppingCart.add(inventoryItemWithQuantity)
            return itemDbId >= 0
        }catch (e:SQLiteException){
            throw AppException("Error occurred while adding cartItems")
        }
    }

}
