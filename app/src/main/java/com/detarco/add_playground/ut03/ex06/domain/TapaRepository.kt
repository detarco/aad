package com.detarco.add_playground.ut03.ex06.domain

interface TapaRepository {

    /**
     * Obtiene un listado de tapas
     */
    fun fetchTapas(): Result<List<TapaModel>>

    /**
     * Obtiene una única tapa
     */
    fun fetchTapa(tapaId: String): Result<TapaModel>
}