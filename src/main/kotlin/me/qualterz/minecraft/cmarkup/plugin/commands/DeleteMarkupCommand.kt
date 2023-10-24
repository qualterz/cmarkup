package me.qualterz.minecraft.cmarkup.plugin.commands

import me.qualterz.minecraft.cmarkup.plugin.services.abstractions.IMarkupStorage
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class DeleteMarkupCommand(
    private val markupStorage: IMarkupStorage
) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        val markupName = args?.getOrNull(0)?.lowercase()

        if (markupName == null) {
            sender.sendMessage(Component.text("Please specify markup name"))
            return false
        }

        if (!markupStorage.isMarkupExists(markupName)) {
            sender.sendMessage(Component.text("Markup does not exists"))
            return false
        }

        markupStorage.removeMarkup(markupName)

        sender.sendMessage("Markup successfully deleted")

        return true
    }
}