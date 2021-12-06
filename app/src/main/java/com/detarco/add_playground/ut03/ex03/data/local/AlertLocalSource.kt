package com.detarco.add_playground.ut03.ex03.data.local

import com.detarco.add_playground.ut03.ex03.domain.AlertModel

interface AlertLocalSource {
    /**
     * Obtengo un listado de alertas. Devuelve modelos de dominio.
     */
    fun findAll(): List<AlertModel>

    fun save(alerts: List<AlertModel>)

    fun save(alert: AlertModel)

    fun findById(alertId: String): AlertModel?
}