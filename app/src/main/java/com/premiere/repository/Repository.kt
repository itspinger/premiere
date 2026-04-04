package com.premiere.repository

import com.premiere.model.Identifiable

interface Repository<I, E : Identifiable<I>> {

    suspend fun findAll(id: I): List<E>

    suspend fun findById(id: I): E

}