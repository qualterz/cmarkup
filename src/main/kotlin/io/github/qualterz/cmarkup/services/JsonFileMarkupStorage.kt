package io.github.qualterz.cmarkup.services

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import io.github.qualterz.cmarkup.data.ContainerMarkup
import io.github.qualterz.cmarkup.abstractions.IMarkupStorage
import java.io.File

class JsonFileMarkupStorage(
    private val destDir: File
) : IMarkupStorage {
    override fun removeMarkup(name: String) {
        File(destDir, "$name.json").delete()
    }

    override fun saveMarkup(name: String, markup: ContainerMarkup) {
        destDir.mkdirs()

        File(destDir, "$name.json").apply {
            createNewFile()
            writeText(Json.encodeToString(markup))
        }
    }

    override fun loadMarkup(name: String): ContainerMarkup {
        val jsonString = File(destDir, "$name.json").readText()
        return Json.decodeFromString(jsonString)
    }

    override fun listMarkups(): Array<String> {
        return destDir.listFiles()?.map {
            it.name.replace(".json", String())
        }?.toTypedArray() ?: emptyArray()
    }

    override fun isMarkupExists(name: String): Boolean {
        File(destDir, "$name.json").let {
            return it.exists() and it.isFile
        }
    }
}