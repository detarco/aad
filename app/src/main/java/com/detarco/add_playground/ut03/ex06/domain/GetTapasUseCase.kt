package com.detarco.add_playground.ut03.ex06.domain

import javax.inject.Inject

/**
 * Caso de uso que devuelve un listado de tapas.
 */
class GetTapasUseCase @Inject constructor(private val tapaRepository: TapaRepository) {

    fun execute(): Result<List<TapaModel>> {
        return tapaRepository.fetchTapas()
    }
}