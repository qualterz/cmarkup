package me.qualterz.minecraft.cmarkup.core.primitives

import kotlinx.serialization.Serializable
import org.bukkit.event.inventory.InventoryType

@Serializable
data class ContainerMarkup(
    val title: String?,
    val size: Int?,
    val type: InventoryType?,
    val slots: Map<String, Collection<Int>>
)