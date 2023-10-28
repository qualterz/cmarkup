package io.github.qualterz.cmarkup.listeners

import io.github.qualterz.cmarkup.PlayersMarkupNames
import io.github.qualterz.cmarkup.PlayersSlotKeys
import io.github.qualterz.cmarkup.inventoryholders.MarkupViewInventory
import io.github.qualterz.cmarkup.abstractions.IMarkupStorage
import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class InventoryListener(
    private val playersSlotKeys: PlayersSlotKeys,
    private val playersMarkupNames: PlayersMarkupNames,
    private val markupStorage: IMarkupStorage
) : Listener {
    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        val inventory = event.clickedInventory

        val inventoryHolder = inventory?.getHolder(false)

        if (inventory == null || inventoryHolder !is MarkupViewInventory)
            return

        event.isCancelled = true

        val playerSlotKey = playersSlotKeys[event.whoClicked.uniqueId]

        if (playerSlotKey == null) {
            event.whoClicked.sendMessage(Component.text("Slot key need to be specified before edit"))
            return
        }

        val markup = inventoryHolder.markup

        when {
            event.isLeftClick -> {
                if (!markup.slots.containsKey(playerSlotKey))
                    markup.slots[playerSlotKey] = HashSet()

                markup.slots[playerSlotKey]!!.add(event.slot)

                inventoryHolder.setSlotKey(event.slot, playerSlotKey)
            }
            event.isRightClick -> {
                markup.slots[playerSlotKey]!!.remove(event.slot)

                if (markup.slots[playerSlotKey]!!.isEmpty())
                    markup.slots.remove(playerSlotKey)

                inventoryHolder.unsetSlotKey(event.slot)
            }
        }

        val markupName = playersMarkupNames[event.whoClicked.uniqueId]!!

        markupStorage.saveMarkup(markupName, inventoryHolder.markup)
    }
}