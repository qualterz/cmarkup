package me.qualterz.minecraft.cmarkup.plugin.commands

import me.qualterz.minecraft.cmarkup.core.primitives.ContainerMarkup
import me.qualterz.minecraft.cmarkup.plugin.services.abstractions.IMarkupStorage
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.event.inventory.InventoryType

class CreateMarkupCommand(
    private val markupStorage: IMarkupStorage
) : CommandExecutor {
    private val inventoryCreateSpecifiers = setOf("size", "height", "type")

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        val markupName = args?.getOrNull(0)?.lowercase()
        val inventoryCreateSpecifier = args?.getOrNull(1)?.lowercase()
        val inventorySpecifierValue = args?.getOrNull(2)?.lowercase()

        if (markupName == null) {
            sender.sendMessage(Component.text("Please specify markup name"))
            return false
        }

        if (inventoryCreateSpecifier == null) {
            sender.sendMessage(Component.text("Please specify markup type or size"))
            return false
        }

        if (!inventoryCreateSpecifiers.contains(inventoryCreateSpecifier)) {
            sender.sendMessage(Component.text("Please specify correct specifier"))
            return false
        }

        if (inventorySpecifierValue == null) {
            sender.sendMessage(Component.text("Please specify $inventoryCreateSpecifier value"))
            return false
        }

        var containerSize: Int? = null
        var containerType: InventoryType? = null

        when (inventoryCreateSpecifier) {
            "type" -> {
                try {
                    containerType = InventoryType.valueOf(inventorySpecifierValue.uppercase())
                } catch (exception: IllegalArgumentException) {
                    sender.sendMessage(Component.text("Please specify correct inventory type"))
                    return false
                }
            }

            "size" -> {
                containerSize = inventorySpecifierValue.toIntOrNull() ?: return false

                try {
                    Bukkit.createInventory(null, containerSize)
                } catch (exception: IllegalArgumentException) {
                    sender.sendMessage(Component.text("Size for markup must be a multiple of 9 between 9 and 54 slots"))
                    return false
                }
            }

            "height" -> {
                val heightValue = inventorySpecifierValue.toIntOrNull() ?: return false

                containerSize = heightValue * 9

                try {
                    Bukkit.createInventory(null, containerSize)
                } catch (exception: IllegalArgumentException) {
                    sender.sendMessage(Component.text("Height for markup must be between 1 and 6 rows inclusively"))
                    return false
                }
            }

            else -> return false
        }

        if (markupStorage.isMarkupExists(markupName)) {
            sender.sendMessage(Component.text("Markup with this name already exists"))
            return false
        }

        markupStorage.saveMarkup(
            markupName, ContainerMarkup(
                title = null,
                size = containerSize,
                type = containerType,
                slots = mutableMapOf()
            )
        )

        sender.sendMessage(Component.text("Markup successfully created"))

        return true
    }
}