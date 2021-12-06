package com.detarco.add_playground.ut03.ex03.domain

interface AlertRepository {
    fun fetchAll(): List<AlertModel>
    fun fetchById(alertId: String): AlertModel
}