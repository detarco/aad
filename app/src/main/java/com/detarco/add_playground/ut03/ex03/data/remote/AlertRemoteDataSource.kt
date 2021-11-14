package com.detarco.add_playground.ut03.ex03.data.remote

import com.detarco.add_playground.ut03.ex03.app.ApiClient
import com.detarco.add_playground.ut03.ex03.domain.AlertModel


class AlertRemoteSource(private val apiClient: ApiClient) {

    fun getAlerts(): List<AlertModel> {
        val alertApiModel = apiClient.getAlerts()
        return alertApiModel.map { apiModel -> apiModel.toDomainModel() }
    }

    fun getAlert(alertId: String): AlertModel? =
        apiClient.getAlert(alertId)?.toDomainModel()

}