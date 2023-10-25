package me.qualterz.minecraft.cmarkup.plugin.services.abstractions

import me.qualterz.minecraft.cmarkup.plugin.data.ContainerMarkup

interface IMarkupStorage {
    fun removeMarkup(name: String)
    fun saveMarkup(name: String, markup: ContainerMarkup)
    fun loadMarkup(name: String): ContainerMarkup
    fun listMarkups(): Array<String>
    fun isMarkupExists(name: String): Boolean
}