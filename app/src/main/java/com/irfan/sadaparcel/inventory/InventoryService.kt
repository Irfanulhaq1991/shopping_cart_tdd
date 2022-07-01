package com.irfan.sadaparcel.inventory

interface InventoryService {
 suspend fun getInventoryItems():List<InventoryItemWithQuantity>
}
