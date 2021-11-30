package com.detarco.add_playground.ut03.ex04.data.db

import android.content.Context
import com.detarco.add_playground.ut03.ex04.app.Ut03Ex04DataBase
import com.detarco.add_playground.ut03.ex04.data.CustomerLocalSource
import com.detarco.add_playground.ut03.ex04.domain.CustomerModel

class CustomerDBLocalSource(private val appContext: Context) : CustomerLocalSource {

    private val db: Ut03Ex04DataBase by lazy {

        Ut03Ex04DataBase.getInstance(appContext)

    }

    override fun save(customer: CustomerModel) {
        TODO("Not yet implemented")
    }

    override fun update(customerId: String): CustomerModel? {
        TODO("Not yet implemented")
    }

    override fun delete(customerId: String): CustomerModel? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<CustomerModel> {
        TODO("Not yet implemented")
    }

    override fun findById(customerId: String): CustomerModel? {
        TODO("Not yet implemented")
    }


}