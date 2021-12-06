package com.detarco.add_playground.ut03.ex04.domain.repository

import com.detarco.add_playground.ut03.ex04.domain.CustomerModel

interface CustomerRepository {

    fun save(customer: CustomerModel)

    //update
    fun update(customerId: Int): CustomerModel?

    //delete
    fun delete(customerId: Int): CustomerModel?

    fun findAll(): List<CustomerModel>

    fun findById(customerId: Int): CustomerModel?

    fun save(customers: List<CustomerModel>)
}