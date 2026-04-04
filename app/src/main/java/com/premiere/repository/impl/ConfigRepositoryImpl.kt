package com.premiere.repository.impl

import com.premiere.api.PremiereApi
import com.premiere.model.ConfigEntry
import com.premiere.repository.ConfigRepository

class ConfigRepositoryImpl(private val api: PremiereApi) : ConfigRepository {

    override suspend fun getConfig(): List<ConfigEntry> = api.getConfig()
}
