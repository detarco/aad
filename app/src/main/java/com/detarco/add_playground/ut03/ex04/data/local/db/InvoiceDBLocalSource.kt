package com.detarco.add_playground.ut03.ex04.data.local.db

import android.content.Context
import com.detarco.add_playground.ut03.ex04.app.Ut03Ex04DataBase
import com.detarco.add_playground.ut03.ex04.data.local.CustomerLocalSource
import com.detarco.add_playground.ut03.ex04.domain.CustomerModel

class InvoiceDBLocalSource(private val appContext: Context) : CustomerLocalSource {

    private val db: Ut03Ex04DataBase by lazy {

        Ut03Ex04DataBase.getInstance(appContext)

    }

    override fun save(customer: CustomerModel) {
        TODO("Not yet implemented")
    }

    override fun save(customers: List<CustomerModel>) {
        TODO("Not yet implemented")
    }

    override fun update(customerId: Int): CustomerModel? {
        TODO("Not yet implemented")
    }

    override fun delete(customerId: Int): CustomerModel? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<CustomerModel> {
        TODO("Not yet implemented")
    }

    override fun findById(customerId: Int): CustomerModel? {
        TODO("Not yet implemented")
    }
}