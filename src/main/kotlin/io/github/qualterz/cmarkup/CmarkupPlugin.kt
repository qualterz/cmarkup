package io.github.qualterz.cmarkup

import io.github.qualterz.cmarkup.abstractions.IMarkupStorage
import io.github.qualterz.cmarkup.commands.*
import io.github.qualterz.cmarkup.data.ContainerMarkup
import io.github.qualterz.cmarkup.listeners.InventoryListener
import io.github.qualterz.cmarkup.listeners.SessionListener
import io.github.qualterz.cmarkup.services.JsonFileMarkupStorage
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
        getCommand("title")?.setExecutor(SetMarkupTitleCommand(markupStorage))
        getCommand("create")?.setExecutor(CreateMarkupCommand(markupStorage))
        getCommand("delete")?.setExecutor(DeleteMarkupCommand(markupStorage))
        getCommand("rename")?.setExecutor(RenameMarkupCommand(markupStorage))
        getCommand("slots")?.setExecutor(ListMarkupSlotsCommand(markupStorage))
        getCommand("list")?.setExecutor(ListMarkupsCommand(markupStorage))
        getCommand("types")?.setExecutor(ListInventoryTypesCommand())
    }

    fun getMarkup(name: String): ContainerMarkup? {
        return if (markupStorage.isMarkupExists(name))
            markupStorage.loadMarkup(name)
        else null
    }
}