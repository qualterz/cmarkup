package io.github.qualterz.cmarkup.data

import kotlinx.serialization.Serializable
import org.bukkit.event.inventory.InventoryType

typealias Slot = Int
typealias SlotKey = String

@Serializable
data class ContainerMarkup(
    var title: String?,
    var titleColor: String?,
    val size: Int?,
    val type: InventoryType?,
    val slots: MutableMap<SlotKey, MutableSet<Slot>>
)