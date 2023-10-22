package me.qualterz.minecraft.cmarkup.plugin.commands

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType

class CreateContainerMarkupCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false
        if (args == null) return false

        val markupNameIndex = 0
        val containerClassifierIndex = 1
        val containerClassifierValueIndex = 2

        val markupName = args.getOrNull(markupNameIndex) ?: return false
        val containerClassifier = args.getOrNull(containerClassifierIndex) ?: return false
        val containerClassifierValue = args.getOrNull(containerClassifierValueIndex)?.uppercase() ?: return false

        when (containerClassifier) {
            "type" -> {
                try {
                    val inventoryType = InventoryType.valueOf(containerClassifierValue)

                    sender.openInventory(Bukkit.createInventory(sender, inventoryType))
                } catch (exception: IllegalArgumentException) {
                    return false
                }
            }

            "size" -> {
                val inventorySize = containerClassifierValue.toIntOrNull() ?: return false

                try {
                    sender.openInventory(Bukkit.createInventory(sender, inventorySize))
                } catch (exception: IllegalArgumentException) {
                    sender.sendMessage(Component.text("Size for custom container must be a multiple of 9 between 9 and 54 slots (got $inventorySize)"))
                    return false
                }
            }

            "height" -> {
                val heightValue = containerClassifierValue.toIntOrNull() ?: return false
                val inventorySize = heightValue * 9

                try {
                    sender.openInventory(Bukkit.createInventory(sender, inventorySize))
                } catch (exception: IllegalArgumentException) {
                    sender.sendMessage(Component.text("Height for custom container must be between 1 and 6 rows inclusively (got $heightValue)"))
                    return false
                }
            }

            else -> return false
        }

        return true
    }
}