package com.detarco.add_playground.ut03.ex03.data

import com.detarco.add_playground.ut03.ex03.data.remote.AlertRemoteSource
import com.detarco.add_playground.ut03.ex03.domain.AlertModel
import com.detarco.add_playground.ut03.ex03.domain.AlertRepository

class AlertDataRepository(
    private val localSource: AlertLocalSource<XXXXX>,
    private val remoteSource: AlertRemoteSource
    ) : AlertRepository {

    override fun fetchAll(): List<AlertModel> {
        var alerts = localSource.getAlerts()
        if (alerts == null){
            alerts = remoteSource.getAlert()
        }
        return alerts
    }

    override fun fetchById(alertId: String): AlertModel {
        val alerts = remoteSource.getAlert(alertId)
        TODO("Not yet implemented")
    }
}