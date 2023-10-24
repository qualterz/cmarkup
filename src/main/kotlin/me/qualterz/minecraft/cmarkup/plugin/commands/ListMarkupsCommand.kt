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
        sender.sendMessage(Component.text(markupStorage.listMarkups().joinToString(separator = "\n")))

        return true
    }
}
