package me.qualterz.minecraft.cmarkup.plugin.commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class OpenContainerCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            val size = args?.firstOrNull()?.toIntOrNull() ?: 9

            sender.openInventory(Bukkit.createInventory(sender, size))
        }

        return true
    }
}