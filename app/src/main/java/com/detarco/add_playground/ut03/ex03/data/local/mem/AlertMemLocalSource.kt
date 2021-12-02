package com.detarco.add_playground.ut03.ex03.data.local.mem

import com.detarco.add_playground.ut03.ex03_b.ex03.data.local.AlertLocalSource
import com.detarco.add_playground.ut03.ex03_v2.domain.AlertModel

class AlertFileLocalSource : AlertLocalSource {

    private val dataStorage = mutableListOf<AlertModel>()

    override fun findAll(): List<AlertModel> {
        /**
        dataStorage.forEach {
            return mutableListOf(it)
        }
        */
        return dataStorage
    }

    override fun save(alerts: List<AlertModel>) {
        alerts.forEach {
            dataStorage.add(it)
        }
    }

    override fun save(alert: AlertModel) {
        dataStorage.add(alert)
    }

    override fun findById(alertId: String): AlertModel? {
        return dataStorage.firstOrNull() { it.id == alertId }
    }

}