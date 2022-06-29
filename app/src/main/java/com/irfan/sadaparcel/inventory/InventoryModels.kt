package com.irfan.sadaparcel.inventory

// Domain Entity
data class InventoryItem(val id: String,
                         val name:String,
                         val description:String,
                         val price:Double,
                         )
//Domain aggregate
data class InventoryItemWithQuantity(val item: InventoryItem, val quantity: Int)