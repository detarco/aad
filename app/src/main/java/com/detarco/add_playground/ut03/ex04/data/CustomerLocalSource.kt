package com.detarco.add_playground.ut03.ex04.data

import com.detarco.add_playground.ut03.ex04.domain.CustomerModel

interface CustomerLocalSource {

    fun save(customer: CustomerModel)

    //update
    fun update(customerId: String): CustomerModel?

    //delete
    fun delete(customerId: String): CustomerModel?

    fun findAll(): List<CustomerModel>

    fun findById(customerId: String): CustomerModel?

    //fun save(customers: List<CustomerModel>)
}