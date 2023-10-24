package me.qualterz.minecraft.cmarkup.plugin

import me.qualterz.minecraft.cmarkup.plugin.commands.*
import me.qualterz.minecraft.cmarkup.plugin.listeners.InventoryListener
import me.qualterz.minecraft.cmarkup.plugin.listeners.SessionListener
import me.qualterz.minecraft.cmarkup.plugin.services.JsonFileMarkupStorage
import me.qualterz.minecraft.cmarkup.plugin.services.abstractions.IMarkupStorage
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*

typealias UUIDStringHashMap = HashMap<UUID, String>
typealias PlayersSlotKeys = UUIDStringHashMap
typealias PlayersMarkupNames = UUIDStringHashMap

class CmarkupPlugin : JavaPlugin(), Listener {
    private val markupStorage: IMarkupStorage = JsonFileMarkupStorage(File(dataFolder, "markups"))
    private val playersSlotKeys = PlayersSlotKeys()
    private val playersMarkupNames = PlayersMarkupNames()

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)
        Bukkit.getPluginManager().registerEvents(InventoryListener(playersSlotKeys, playersMarkupNames, markupStorage), this)
        Bukkit.getPluginManager().registerEvents(SessionListener(playersSlotKeys, playersMarkupNames), this)

        getCommand("open")?.setExecutor(OpenMarkupCommand(playersSlotKeys, playersMarkupNames, markupStorage))
        getCommand("create")?.setExecutor(CreateMarkupCommand(markupStorage))
        getCommand("delete")?.setExecutor(DeleteMarkupCommand(markupStorage))
        getCommand("slots")?.setExecutor(ListMarkupSlotsCommand(markupStorage))
        getCommand("list")?.setExecutor(ListMarkupsCommand(markupStorage))
        getCommand("types")?.setExecutor(ListInventoryTypesCommand())
    }
}