package com.irfan.sadaparcel

import com.irfan.sadaparcel.inventory.InventoryItem
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

object  DummyDataProvider{
    val data: List<InventoryItemWithQuantity> = listOf(
            InventoryItemWithQuantity(InventoryItem("1","item1","Description",2.1),1),
            InventoryItemWithQuantity(InventoryItem("2","item2","Description",2.2),1),
            InventoryItemWithQuantity(InventoryItem("3","item3","Description",2.4),1),
            InventoryItemWithQuantity(InventoryItem("4","item4","Description",2.3),1),
            InventoryItemWithQuantity(InventoryItem("5","item5","Description",2.3),1),
            InventoryItemWithQuantity(InventoryItem("6","item6","Description",2.3),1),
            InventoryItemWithQuantity(InventoryItem("7","item7","Description",2.3),1),
            InventoryItemWithQuantity(InventoryItem("8","item8","Description",2.3),1),
            InventoryItemWithQuantity(InventoryItem("9","item9","Description",2.3),1),
            InventoryItemWithQuantity(InventoryItem("10","item10","Description",2.3),1),
            InventoryItemWithQuantity(InventoryItem("11","item11","Description",2.3),1),
            InventoryItemWithQuantity(InventoryItem("12","item12","Description",2.3),1),
            InventoryItemWithQuantity(InventoryItem("13","item13","Description",2.3),1),
            InventoryItemWithQuantity(InventoryItem("14","item14","Description",2.3),1),
        )
}