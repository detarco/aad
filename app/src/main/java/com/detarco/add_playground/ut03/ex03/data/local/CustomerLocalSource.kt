package com.detarco.add_playground.ut03.ex03.data.local

import android.content.Context
import com.detarco.add_playground.ut03.ex03.app.Ut03Ex03DataBase
import com.detarco.add_playground.ut03.ex03.data.local.entity.ClothesEntity
import com.detarco.add_playground.ut03.ex03.data.local.entity.CustomerEntity
import com.detarco.add_playground.ut03.ex03.domain.CustomerModel


class CustomerLocalSource(applicationContext: Context) {

    private val db = Ut03Ex03DataBase.getInstance(applicationContext)

    init {
        Thread {
            db.clearAllTables()
        }.start()
        Thread.sleep(1000)
    }

    fun findAll(): List<CustomerModel> {

        val customerAndClothes = db.customerDao().getCustomerAndClothes()

        return customerAndClothes.map { element -> element.toModel() }

    }

/*
    //Tuve problemas con esta función, 'CustomerModel.toEntity' no se reconocía

    fun save(customerModel: CustomerModel) {
        db.customerDao().insertCustomerAndClothes(
            CustomerModel.toEntity(customerModel.toEntity),
            ClothesEntity.toEntity(customerModel.clothesModel, customerModel.id)
        )
    }

 */

    fun save2way(customerModel: CustomerModel) {
        db.runInTransaction {
            val customerId = db.customerDao().insert(CustomerEntity.toEntity(customerModel))
            db.clothesDao().insert(ClothesEntity.toEntity(customerModel.clothesModel, customerId.toInt()))
        }
    }
}