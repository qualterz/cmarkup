package me.qualterz.minecraft.cmarkup.plugin.commands

import me.qualterz.minecraft.cmarkup.plugin.services.abstractions.IMarkupStorage
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class ListMarkupsCommand(
    val markupStorage: IMarkupStorage
) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        val markups = markupStorage.listMarkups()

        sender.sendMessage(
            if (markups.isEmpty()) {
                Component.text("No slots present in markup")
            } else {
                Component.text(markups.joinToString(separator = "\n"))
            }
        )

        return true
    }
}
