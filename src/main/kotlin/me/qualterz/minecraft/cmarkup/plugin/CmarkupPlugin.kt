package me.qualterz.minecraft.cmarkup.plugin

import me.qualterz.minecraft.cmarkup.plugin.commands.CreateContainerMarkupCommand
import me.qualterz.minecraft.cmarkup.plugin.commands.ListContainerTypesCommand
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class CmarkupPlugin : JavaPlugin(), Listener {
    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)

        getCommand("create")?.setExecutor(CreateContainerMarkupCommand())
        getCommand("types")?.setExecutor(ListContainerTypesCommand())
    }
}