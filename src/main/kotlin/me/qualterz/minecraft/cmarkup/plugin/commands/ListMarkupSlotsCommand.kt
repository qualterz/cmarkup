package me.qualterz.minecraft.cmarkup.plugin.commands

import me.qualterz.minecraft.cmarkup.plugin.abstractions.IMarkupStorage
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class ListMarkupSlotsCommand(
    val markupStorage: IMarkupStorage
) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        val markupName = args?.getOrNull(0)?.lowercase() ?: return false

        if (!markupStorage.isMarkupExists(markupName)) {
            sender.sendMessage(Component.text("Markup does not exists"))
            return false
        }

        val markup = markupStorage.loadMarkup(markupName)

        sender.sendMessage(
            if (markup.slots.keys.isEmpty()) {
                Component.text("No slots present in markup")
            } else {
                Component.text(markup.slots.keys.joinToString(separator = "\n"))
            }
        )

        return true
    }
}
