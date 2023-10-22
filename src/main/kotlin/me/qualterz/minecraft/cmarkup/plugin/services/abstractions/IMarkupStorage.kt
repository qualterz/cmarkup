package me.qualterz.minecraft.cmarkup.plugin.services.abstractions

import me.qualterz.minecraft.cmarkup.core.primitives.ContainerMarkup

interface IMarkupStorage {
    fun saveMarkup(name: String, markup: ContainerMarkup)
    fun loadMarkup(name: String): ContainerMarkup
}