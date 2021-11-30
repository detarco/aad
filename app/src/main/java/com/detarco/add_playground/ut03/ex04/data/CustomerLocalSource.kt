package com.detarco.add_playground.ut03.ex04.data

import com.detarco.add_playground.ut03.ex04.domain.CustomerModel

interface CustomerLocalSource {
    /**
     * Obtengo un listado de alertas. Devuelve modelos de dominio.
     */
    fun findAll(): List<CustomerModel>

    fun save(alerts: List<CustomerModel>)

    fun save(alert: CustomerModel)

    fun findById(customerId: String): CustomerModel?
}