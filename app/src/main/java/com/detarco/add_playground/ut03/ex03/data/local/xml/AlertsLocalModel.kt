package com.detarco.add_playground.ut03.ex03.data.local.xml

import com.detarco.add_playground.ut03.ex03_b.ex03.app.storage.LocalModel
import com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel
import com.detarco.add_playground.ut03.ex03_v2.domain.AlertModel

class AlertsLocalModel(
    val alertId: String,
    val alerts: List<AlertModel>
    ) : LocalModel {
    override fun getId(): String = ID

    companion object {
        val ID: String = AlertsLocalModel::class.java.simpleName
    }
}