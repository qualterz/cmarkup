package me.qualterz.minecraft.cmarkup.plugin.inventoryholders

import me.qualterz.minecraft.cmarkup.core.primitives.ContainerMarkup
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class MarkupInventory(
    val markup: ContainerMarkup
) : InventoryHolder{
    private val inventory: Inventory = if (markup.title != null && markup.type != null)
        Bukkit.createInventory(this, markup.type, Component.text(markup.title))
    else if (markup.type != null)
        Bukkit.createInventory(this, markup.type)
    else if (markup.title != null && markup.size != null)
        Bukkit.createInventory(this, markup.size, Component.text(markup.title))
    else if (markup.size != null)
        Bukkit.createInventory(this, markup.size)
    else throw IllegalArgumentException("Cannot construct container with markup: $markup")

    private val slotKeyMaterial = Material.BARRIER

    override fun getInventory(): Inventory {
        return inventory
    }

    fun setSlotKey(index: Int, key: String) {
        inventory.setItem(index, ItemStack(slotKeyMaterial).apply {
            editMeta { it.displayName(Component.text(key)) }
        })
    }

    fun unsetSlotKey(index: Int) {
        inventory.setItem(index, ItemStack(Material.AIR))
    }

    fun setSlotKeyView(key: String) {
        markup.slots[key]?.forEach{ setSlotKey(it, key) }
    }
}