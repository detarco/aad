package com.detarco.add_playground.ut03.ex03.domain

interface CustomerRepository {

    fun saveCustomer(customerModel: CustomerModel)
    fun fetchAll(): List<CustomerModel>

}