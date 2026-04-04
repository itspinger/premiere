package com.premiere.model

import kotlinx.serialization.Serializable

@Serializable
data class ConfigEntry(
    val key: String,
    val value: String
)
