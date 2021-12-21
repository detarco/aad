package com.detarco.add_playground.ut03.ex06.domain

/**
 * Caso de uso que devuelve una tapa
 */
class GetTapaUseCase(private val repository: TapaRepository) {

    fun execute(tapaId: String): Result<TapaModel> {
        return repository.fetchTapa(tapaId)
    }
}