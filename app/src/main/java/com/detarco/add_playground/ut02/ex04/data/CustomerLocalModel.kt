package com.detarco.add_playground.ut02.ex04.data

import com.detarco.add_playground.ut02.ex04.domain.CustomerModel


class CustomerLocalModel(
    val alertId: String,
    val alerts: List<CustomerModel>
) : CustomerLocalSource {
    fun getId(): String = ID

    companion object {
        val ID: String = CustomerLocalModel::class.java.simpleName
    }
}