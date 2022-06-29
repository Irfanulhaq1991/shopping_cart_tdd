package com.irfan.sadaparcel.inventory

interface InventoryService {
 fun getInventoryItems():List<InventoryItemWithQuantity>
}
