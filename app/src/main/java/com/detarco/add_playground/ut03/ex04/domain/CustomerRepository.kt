package com.detarco.add_playground.ut03.ex04.domain

interface CustomerRepository{
    fun fetchAllCustomers(): List<CustomerModel>
    fun saveCustomer(customer: CustomerModel)
}