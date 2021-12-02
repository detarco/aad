package com.detarco.add_playground.ut03.ex04.data

import com.detarco.add_playground.ut03.ex04.data.local.db.CustomerDBLocalSource
import com.detarco.add_playground.ut03.ex04.domain.CustomerModel
import com.detarco.add_playground.ut03.ex04.domain.CustomerRepository

class CustomerDataRepository(
    private val customerDBLocalSource: CustomerDBLocalSource
) : CustomerRepository {

    override fun fetchAllCustomers(): List<CustomerModel> {
        return customerDBLocalSource.findAll()
    }

    override fun saveCustomer(customer: CustomerModel) {
        customerDBLocalSource.save(customer)
    }

}