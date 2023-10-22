package me.qualterz.minecraft.cmarkup.core.primitives.abstractions

import org.bukkit.event.inventory.InventoryType

interface IContainerMarkup {
    val title: String?
    val size: Int?
    val type: InventoryType?
    val slots: HashMap<String, Array<Int>>
}