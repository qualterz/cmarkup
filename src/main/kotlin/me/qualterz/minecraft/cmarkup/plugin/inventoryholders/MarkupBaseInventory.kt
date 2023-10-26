package me.qualterz.minecraft.cmarkup.plugin.inventoryholders

import me.qualterz.minecraft.cmarkup.plugin.data.ContainerMarkup
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

open class MarkupBaseInventory(
    val markup: ContainerMarkup
) : InventoryHolder {
    private val inventory: Inventory = if (markup.title != null && markup.type != null)
        Bukkit.createInventory(this, markup.type, Component.text(markup.title.toString(),
            markup.titleColor?.let { TextColor.fromCSSHexString(it) }))
    else if (markup.type != null)
        Bukkit.createInventory(this, markup.type)
    else if (markup.title != null && markup.size != null)
        Bukkit.createInventory(this, markup.size, Component.text(markup.title.toString(),
            markup.titleColor?.let { TextColor.fromCSSHexString(it) }))
    else if (markup.size != null)
        Bukkit.createInventory(this, markup.size)
    else throw IllegalArgumentException("Cannot create inventory for markup: $markup")

    override fun getInventory(): Inventory {
        return inventory
    }
}