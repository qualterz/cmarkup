package me.qualterz.minecraft.cmarkup.plugin.commands

import me.qualterz.minecraft.cmarkup.plugin.services.abstractions.IMarkupStorage
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class SetMarkupTitleCommand(
    val markupStorage: IMarkupStorage
) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        val markupName = args?.getOrNull(0)?.lowercase()
        val markupTitle = args?.getOrNull(1)
        val markupTitleColor = args?.getOrNull(2)

        if (markupName == null) {
            sender.sendMessage(Component.text("Please specify markup name"))
            return false
        }

        if (markupTitle == null) {
            sender.sendMessage(Component.text("Please specify inventory title"))
            return false
        }

        if (!markupStorage.isMarkupExists(markupName)) {
            sender.sendMessage(Component.text("Markup does not exists"))
            return false
        }

        val markup = markupStorage.loadMarkup(markupName)

        markup.title = markupTitle
        markup.titleColor = markupTitleColor

        markupStorage.saveMarkup(markupName, markup)

        sender.sendMessage(Component.text("Markup inventory title successfully changed"))

        return true
    }
}