package me.qualterz.minecraft.cmarkup.plugin.listeners

import me.qualterz.minecraft.cmarkup.plugin.PlayersMarkupNames
import me.qualterz.minecraft.cmarkup.plugin.PlayersSlotKeys
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class SessionListener (
    val playersSlotKeys: PlayersSlotKeys,
    val playersMarkupNames: PlayersMarkupNames
): Listener {
    @EventHandler
    fun onPlayerLeave(event: PlayerQuitEvent) {
        val playerId = event.player.uniqueId

        playersSlotKeys.remove(playerId)
        playersMarkupNames.remove(playerId)
    }
}