package com.detarco.add_playground.ut03.ex03.data.local.xml

import com.detarco.add_playground.ut03.ex03_b.ex03.app.storage.LocalStorage
import com.detarco.add_playground.ut03.ex03_b.ex03.data.local.AlertLocalSource
import com.detarco.add_playground.ut03.ex03_v2.domain.AlertModel

class AlertXmlLocalSource2(
    private val localStorage: LocalStorage<com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel>
    ) : AlertLocalSource {

    override fun findAll(): List<AlertModel> {
        val alerts = localStorage.fetch(AlertsLocalModel.ID, com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel::class.java)
        return alerts?.alerts ?: mutableListOf()
    }

    override fun save(alerts: List<AlertModel>) {
        val localAlertModel =
            com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel(
                AlertsLocalModel.ID,
                alerts
            )
        localStorage.save(localAlertModel, com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel::class.java)
    }

    override fun save(alert: AlertModel) {
        localStorage.save(
            com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel(
                alert.id,
                mutableListOf(alert)
            ),
            com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel::class.java)
    }

    override fun findById(alertId: String): AlertModel? {
        val localModel = localStorage.fetch(alertId, com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel::class.java)
        return localModel?.alerts?.firstOrNull()
    }
}