package com.detarco.add_playground.ut03.ex03.app.api

import com.detarco.add_playground.ut03.ex03.data.remote.AlertApiModel

interface ApiClient {
    fun getAlerts(): List<AlertApiModel>
    fun getAlert(alertId: String): AlertApiModel?
}