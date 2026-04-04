package com.premiere.repository

import com.premiere.model.ConfigEntry

interface ConfigRepository {

    suspend fun getConfig(): List<ConfigEntry>
}
