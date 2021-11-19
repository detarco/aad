package com.detarco.add_playground.ut03.ex03.data.local.mem

import com.detarco.add_playground.ut03.ex03.data.local.AlertLocalSource
import com.detarco.add_playground.ut03.ex03.domain.AlertModel

class AlertFileLocalSource : AlertLocalSource {

    override fun findAll(): List<AlertModel> {
        TODO("Not yet implemented")
    }

    override fun save(alerts: List<AlertModel>) {
        TODO("Not yet implemented")
    }

    override fun save(alert: AlertModel) {
        TODO("Not yet implemented")
    }

    override fun findById(alertId: String): AlertModel? {
        TODO("Not yet implemented")
    }

}