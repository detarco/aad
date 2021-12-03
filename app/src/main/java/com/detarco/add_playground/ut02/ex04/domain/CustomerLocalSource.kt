package com.detarco.add_playground.ut02.ex04.domain

interface CustomerLocalSource {
    /**
     * Obtengo un listado de customers. Devuelve modelos de dominio.
     */
    fun save(customerModel: CustomerModel)

    fun save(customersList: List<CustomerModel>)

    fun delete (customerModelId : Int)

    fun findAll(): List<CustomerModel>

    fun findById(customerId: String): CustomerModel?
}