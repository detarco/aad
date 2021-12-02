package com.detarco.add_playground.ut03.ex03.domain

import com.detarco.add_playground.ut03.ex03_v2.domain.AlertModel

interface AlertRepository {
    fun fetchAll(): List<AlertModel>
    fun fetchById(alertId: String): AlertModel
}