package com.irfan.sadaparcel

import com.irfan.sadaparcel.inventory.InventoryItem
import com.irfan.sadaparcel.inventory.InventoryItemWithQuantity

object  DummyDataProvider{
    val data: List<InventoryItemWithQuantity> =

        listOf(
            InventoryItemWithQuantity(
                InventoryItem(
                    "1", "item1",
                    "Description",
                    2.1
                ), 1
            ),
            InventoryItemWithQuantity(
                InventoryItem(
                    "1", "item1",
                    "Description",
                    2.1
                ), 1
            ),
            InventoryItemWithQuantity(
                InventoryItem(
                    "1", "item1",
                    "Description",
                    2.1
                ), 1
            )
        )
}