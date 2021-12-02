package com.detarco.add_playground.ut03.ex03.data.local.xml

import android.content.Context
import com.detarco.add_playground.ut03.ex03.app.serializer.GsonSerializer
import com.detarco.add_playground.ut03.ex03_b.ex03.app.storage.LocalStorage
import com.detarco.add_playground.ut03.ex03_b.ex03.data.local.AlertLocalSource
import com.detarco.add_playground.ut03.ex03_v2.domain.AlertModel

class AlertXmlLocalSource(
    private val localStorage: LocalStorage<com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel>,
    private val context: Context,
    private val serializer: GsonSerializer
    )
    : AlertLocalSource {

    private val nameXmlFile = "ut03_ex03_alerts"
    private val sharedPref = context.getSharedPreferences(nameXmlFile, Context.MODE_PRIVATE)

    override fun findAll(): List<AlertModel> {
        val alertsLocalModel = fetchInXml(AlertsLocalModel.ID, com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel::class.java)
        return alertsLocalModel?.alerts ?: mutableListOf()
    }

    override fun save(alerts: List<AlertModel>) {
        val localAlertModel =
            com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel(
                AlertsLocalModel.ID,
                alerts
            )
        saveInXml(localAlertModel)
    }

    override fun save(alert: AlertModel) {
        saveInXml(
            com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel(
                alert.id,
                mutableListOf(alert)
            )
        )
    }

    override fun findById(alertId: String): AlertModel? {
        val localModel = fetchInXml(alertId, com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel::class.java)
        return localModel?.alerts?.firstOrNull()
    }

    private fun saveInXml(model: com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel) {
        val edit = sharedPref.edit()
        edit?.putString(model.getId(), serializer.toJson(model, com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel::class.java))
        edit?.apply()
    }

    private fun fetchInXml(modelId: String, typeClass: Class<com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel>): com.detarco.add_playground.ut03.ex03_b.ex03.data.local.xml.AlertsLocalModel? {
        val jsonModel = sharedPref.getString(modelId, "{}")
        return if (jsonModel != null) {
            serializer.fromJson(jsonModel, typeClass)
        } else {
            null
        }
    }


}