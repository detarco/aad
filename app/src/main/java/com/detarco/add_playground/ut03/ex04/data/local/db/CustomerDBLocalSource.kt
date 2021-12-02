package com.detarco.add_playground.ut03.ex04.data.local.db

import android.content.Context
import com.detarco.add_playground.ut03.ex04.app.Ut03Ex04DataBase
import com.detarco.add_playground.ut03.ex04.data.local.CustomerLocalSource
import com.detarco.add_playground.ut03.ex04.data.local.db.entity.CustomerEntity
import com.detarco.add_playground.ut03.ex04.domain.CustomerModel

class CustomerDBLocalSource(private val appContext: Context) : CustomerLocalSource {

    private val db: Ut03Ex04DataBase by lazy {

        Ut03Ex04DataBase.getInstance(appContext)

    }

    override  fun save(customer: CustomerModel) {
        db.customerDao().insert(CustomerEntity.toEntity(customer))
    }

    /**
     * Guarda una lista, no pedido en el ejercicio
    override  fun save(customers: List<CustomerModel>) {
        customers.forEach { customerModel ->
            save(customerModel)
        }
    }
    */

    override  fun update(customerId: Int): CustomerModel? {

        val updateCus = db.customerDao().findById(customerId)?.toModel()

        return updateCus.also {
            db.customerDao().insert(CustomerEntity.toEntity(it))
        }

        //return updateCus
    }

    override  fun delete(customerId: Int): CustomerModel? {

        val delCus = db.customerDao().findById(customerId)?.toModel()

        return delCus.apply {
            db.customerDao().delete()
        }

        //return delCus

    }

    override  fun findAll(): List<CustomerModel> {
        val customer = db.customerDao().fetchAll()
        return customer.map { element -> element.toModel() }
    }

    override  fun findById(customerId: Int): CustomerModel? {
        return db.customerDao().findById(customerId)?.toModel()
    }


}