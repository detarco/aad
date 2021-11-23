package com.detarco.add_playground.ut03.ex03.data.local.xml

import com.detarco.add_playground.ut03.ex03.app.storage.LocalStorage
import com.detarco.add_playground.ut03.ex03.data.local.AlertLocalSource
import com.detarco.add_playground.ut03.ex03.domain.AlertModel

class AlertXmlLocalSource2(
    private val localStorage: LocalStorage<AlertsLocalModel>
    ) : AlertLocalSource {

    override fun findAll(): List<AlertModel> {
        val alerts = localStorage.fetch(AlertsLocalModel.ID, AlertsLocalModel::class.java)
        return alerts?.alerts ?: mutableListOf()
    }

    override fun save(alerts: List<AlertModel>) {
        val localAlertModel = AlertsLocalModel(AlertsLocalModel.ID, alerts)
        localStorage.save(localAlertModel, AlertsLocalModel::class.java)
    }

    override fun save(alert: AlertModel) {
        localStorage.save(AlertsLocalModel(alert.id, mutableListOf(alert)),
            AlertsLocalModel::class.java)
    }

    override fun findById(alertId: String): AlertModel? {
        val localModel = localStorage.fetch(alertId, AlertsLocalModel::class.java)
        return localModel?.alerts?.firstOrNull()
    }
}