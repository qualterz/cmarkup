package io.github.qualterz.cmarkup.inventoryholders

import io.github.qualterz.cmarkup.data.ContainerMarkup
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class MarkupViewInventory(
    markup: ContainerMarkup
) : MarkupBaseInventory(markup) {
    private val slotKeyMaterial = Material.BARRIER

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