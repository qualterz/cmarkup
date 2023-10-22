package me.qualterz.minecraft.cmarkup.plugin.commands

import me.qualterz.minecraft.cmarkup.core.primitives.ContainerMarkup
import me.qualterz.minecraft.cmarkup.plugin.services.abstractions.IMarkupStorage
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType

class CreateContainerMarkupCommand(
    private val markupStorage: IMarkupStorage
) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false
        if (args == null) return false

        val markupNameIndex = 0
        val containerClassifierIndex = 1
        val containerClassifierValueIndex = 2

        val markupName = args.getOrNull(markupNameIndex) ?: return false
        val containerClassifier = args.getOrNull(containerClassifierIndex) ?: return false
        val containerClassifierValue = args.getOrNull(containerClassifierValueIndex) ?: return false

        var containerSize: Int? = null
        var containerType: InventoryType? = null

        when (containerClassifier) {
            "type" -> {
                try {
                    containerType = InventoryType.valueOf(containerClassifierValue.uppercase())
                } catch (exception: IllegalArgumentException) {
                    sender.sendMessage(Component.text("Container type is not exists ($containerClassifierValue)"))
                    return false
                }
            }

            "size" -> {
                containerSize = containerClassifierValue.toIntOrNull() ?: return false

                try {
                    Bukkit.createInventory(sender, containerSize)
                } catch (exception: IllegalArgumentException) {
                    sender.sendMessage(Component.text("Size for custom container must be a multiple of 9 between 9 and 54 slots (got $containerSize)"))
                    return false
                }
            }

            "height" -> {
                val heightValue = containerClassifierValue.toIntOrNull() ?: return false

                containerSize = heightValue * 9

                try {
                    Bukkit.createInventory(sender, containerSize)
                } catch (exception: IllegalArgumentException) {
                    sender.sendMessage(Component.text("Height for custom container must be between 1 and 6 rows inclusively (got $heightValue)"))
                    return false
                }
            }

            else -> return false
        }

        markupStorage.saveMarkup(
            markupName, ContainerMarkup(
                title = null,
                size = containerSize,
                type = containerType,
                slots = mapOf()
            )
        )

        return true
    }
}