package com.detarco.add_playground.ut03.ex03.data.local

import com.detarco.add_playground.ut03.ex03.domain.CustomerLocalModel

class CustomerLocalRepository(private val localStorage: LocalStorage<CustomerLocalModel>) {

    fun fetch(): CustomerLocalModel? = localStorage.fetch(CustomerLocalModel.ID)

    fun save(customerLocalModel: CustomerLocalModel) {
        localStorage.save(customerLocalModel)
    }

}