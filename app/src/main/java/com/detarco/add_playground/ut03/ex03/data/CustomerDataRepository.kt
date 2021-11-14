package com.detarco.add_playground.ut03.ex03.data

import com.detarco.add_playground.ut03.ex03.data.local.CustomerLocalSource
import com.detarco.add_playground.ut03.ex03.domain.CustomerModel
import com.detarco.add_playground.ut03.ex03.domain.CustomerRepository


class CustomerDataRepository (private val customerLocalSource: CustomerLocalSource) : CustomerRepository {

    override fun saveCustomer(customerModel: CustomerModel) {
        customerLocalSource.save(customerModel)
    }

    override fun fetchAll(): List<CustomerModel> = customerLocalSource.findAll()

}