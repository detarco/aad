package com.detarco.add_playground.ut03.ex03.domain

import com.detarco.add_playground.ut03.ex03_b.ex03.domain.AlertRepository
import com.detarco.add_playground.ut03.ex03_v2.domain.AlertModel

class GetAlertsUseCase(private val repository: AlertRepository) {
    fun execute(): List<AlertModel> = repository.fetchAll()
}