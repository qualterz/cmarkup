package io.github.qualterz.cmarkup.commands

import io.github.qualterz.cmarkup.abstractions.IMarkupStorage
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RenameMarkupCommand(
    private val markupStorage: IMarkupStorage
) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        val markupName = args?.getOrNull(0)?.lowercase()
        val markupRenameName = args?.getOrNull(1)?.lowercase()

        if (markupName == null) {
            sender.sendMessage(Component.text("Please specify markup"))
            return false
        }

        if (markupRenameName == null) {
            sender.sendMessage(Component.text("Please specify new markup name"))
            return false
        }

        if (!markupStorage.isMarkupExists(markupName)) {
            sender.sendMessage(Component.text("Markup does not exists"))
            return false
        }

        if (markupStorage.isMarkupExists(markupRenameName)) {
            sender.sendMessage(Component.text("Markup with the same name already exists"))
            return false
        }

        val markup = markupStorage.loadMarkup(markupName)

        markupStorage.removeMarkup(markupName)
        markupStorage.saveMarkup(markupRenameName, markup)

        sender.sendMessage("Markup successfully renamed")

        return true
    }
}