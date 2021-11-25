package com.detarco.add_playground.ut02.ex04.data

import com.detarco.add_playground.ut02.ex04.domain.CustomerModel

interface CustomerLocalSource {
    /**
     * Obtengo un listado de customers. Devuelve modelos de dominio.
     */
    fun findAll(): List<CustomerModel>

    fun save(customers: List<CustomerModel>)

    fun save(customer: CustomerModel)

    fun findById(customerId: String): CustomerModel?
}