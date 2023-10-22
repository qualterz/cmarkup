package me.qualterz.minecraft.cmarkup.plugin.commands

import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.event.inventory.InventoryType

class ListContainerTypesCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        sender.sendMessage(Component.text(InventoryType.entries.joinToString(separator = "\n") { it.name }))
        return true
    }
}