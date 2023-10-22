package me.qualterz.minecraft.cmarkup.plugin

import me.qualterz.minecraft.cmarkup.plugin.commands.CreateContainerMarkupCommand
import me.qualterz.minecraft.cmarkup.plugin.commands.ListContainerTypesCommand
import me.qualterz.minecraft.cmarkup.plugin.services.PluginMarkupStorage
import me.qualterz.minecraft.cmarkup.plugin.services.abstractions.IMarkupStorage
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class CmarkupPlugin : JavaPlugin(), Listener {
    val markupStorage: IMarkupStorage

    init {
        markupStorage = PluginMarkupStorage(this)
    }

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)

        getCommand("create")?.setExecutor(CreateContainerMarkupCommand(markupStorage))
        getCommand("types")?.setExecutor(ListContainerTypesCommand())
    }
}