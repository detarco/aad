package com.detarco.add_playground.ut03.ex06.domain

/**
 * Caso de uso que devuelve un listado de tapas.
 */
class GetTapasUseCase(private val repository: TapaRepository) {

    fun execute(): Result<List<TapaModel>> {
        return repository.fetchTapas()
    }
}