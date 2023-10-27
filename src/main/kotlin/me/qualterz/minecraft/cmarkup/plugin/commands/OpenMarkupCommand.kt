package me.qualterz.minecraft.cmarkup.plugin.commands

import me.qualterz.minecraft.cmarkup.plugin.PlayersMarkupNames
import me.qualterz.minecraft.cmarkup.plugin.PlayersSlotKeys
import me.qualterz.minecraft.cmarkup.plugin.inventoryholders.MarkupViewInventory
import me.qualterz.minecraft.cmarkup.plugin.abstractions.IMarkupStorage
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class OpenMarkupCommand(
    private val playersSlotKeys: PlayersSlotKeys,
    private val playersMarkupNames: PlayersMarkupNames,
    private val markupStorage: IMarkupStorage
) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        val markupName = args?.getOrNull(0)?.lowercase()
        val argumentSlotKey = args?.getOrNull(1)

        if (markupName == null) {
            sender.sendMessage(Component.text("Please specify markup"))
            return false
        }

        if (!markupStorage.isMarkupExists(markupName)) {
            sender.sendMessage(Component.text("Markup does not exists"))
            return false
        }

        if (argumentSlotKey != null) {
            playersSlotKeys[sender.uniqueId] = argumentSlotKey
        }

        val markup = markupStorage.loadMarkup(markupName)

        val markupInventory = MarkupViewInventory(markup)

        val playerSlotKey = playersSlotKeys[sender.uniqueId]

        if (playerSlotKey == null) {
            sender.sendMessage(Component.text("Please specify slot key to open markup"))
            return false
        }

        if (markup.slots[playerSlotKey] == null)
            markup.slots[playerSlotKey] = HashSet()

        markupInventory.setSlotKeyView(playerSlotKey)

        playersMarkupNames[sender.uniqueId] = markupName

        sender.openInventory(markupInventory.inventory)

        return true
    }
}