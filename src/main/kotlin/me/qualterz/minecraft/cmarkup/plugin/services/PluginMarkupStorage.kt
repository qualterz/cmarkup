package me.qualterz.minecraft.cmarkup.plugin.services

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.qualterz.minecraft.cmarkup.core.primitives.ContainerMarkup
import me.qualterz.minecraft.cmarkup.plugin.CmarkupPlugin
import me.qualterz.minecraft.cmarkup.plugin.services.abstractions.IMarkupStorage
import java.io.File

class PluginMarkupStorage(
    plugin: CmarkupPlugin
) : IMarkupStorage {
    private val markupsDir = File(plugin.dataFolder, "markups")

    override fun saveMarkup(name: String, markup: ContainerMarkup) {
        markupsDir.mkdirs()

        File(markupsDir, "${name.lowercase()}.json").apply {
            createNewFile()
            writeText(Json.encodeToString(markup))
        }
    }

    override fun loadMarkup(name: String): ContainerMarkup {
        val jsonString = File(markupsDir, "${name.lowercase()}.json").readText()
        return Json.decodeFromString(jsonString)
    }
}