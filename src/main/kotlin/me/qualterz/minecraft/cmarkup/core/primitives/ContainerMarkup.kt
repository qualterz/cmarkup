package me.qualterz.minecraft.cmarkup.core.primitives

import kotlinx.serialization.Serializable
import me.qualterz.minecraft.cmarkup.core.primitives.abstractions.IContainerMarkup
import org.bukkit.event.inventory.InventoryType

@Serializable
data class ContainerMarkup(
    override val title: String?,
    override val size: Int?,
    override val type: InventoryType?,
    override val slots: HashMap<String, Array<Int>>
) : IContainerMarkup