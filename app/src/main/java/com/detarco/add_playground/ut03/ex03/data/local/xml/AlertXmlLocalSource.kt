package com.detarco.add_playground.ut03.ex03.data.local.xml

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.detarco.add_playground.R
import com.detarco.add_playground.commons.Serializer
import com.detarco.add_playground.ut03.ex03.app.serializer.GsonSerializer
import com.detarco.add_playground.ut03.ex03.data.local.AlertLocalSource
import com.detarco.add_playground.ut03.ex03.data.local.LocalStorage
import com.detarco.add_playground.ut03.ex03.domain.AlertModel

class AlertXmlLocalSource(
    private val localStorage: LocalStorage<AlertsLocalModel>,
    private val activity: AppCompatActivity,
    private val serializer: GsonSerializer
    )
    : AlertLocalSource {

    private val sharedPref = activity.getSharedPreferences(
        activity.getString(R.string.ut03_preference_file_exercise03), Context.MODE_PRIVATE
    )

    override fun findAll(): List<AlertModel> {
        TODO("Not yet implemented")
    }

    override fun save(alerts: List<AlertModel>) {

        val edit = sharedPref.edit()

        alerts.forEach {
            edit?.putString(it.id, serializer.toJson.(alerts))
            edit?.apply()
        }

    }

    override fun save(alert: AlertModel) {
        TODO("Not yet implemented")
    }

    override fun findById(alertId: String): AlertModel? {
        TODO("Not yet implemented")
    }

}