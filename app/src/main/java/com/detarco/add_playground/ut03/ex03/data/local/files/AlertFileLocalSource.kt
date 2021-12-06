package com.detarco.add_playground.ut03.ex03.data.local.files

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.detarco.add_playground.ut03.ex03.data.local.AlertLocalSource
import com.detarco.add_playground.ut03.ex03.domain.AlertModel
import java.io.File

class AlertFileLocalSource(
    private val activity: AppCompatActivity
    ) : AlertLocalSource {

    private lateinit var dataSource: AlertLocalSource

    override fun findAll(): List<AlertModel> {
        val file = File(activity.filesDir, "alert.txt")

        //val alerts = mutableListOf<AlertModel>()

        val contentFile = dataSource.findAll()

        file.appendText(contentFile.toString() + "\n")

        Log.d(TAG, "Función findAll() de File en ejecución, se está encontrando todas las alertas")

        return contentFile
    }


    override fun save(alerts: List<AlertModel>) {
        val file = File(activity.filesDir, "alert.txt")

        alerts.forEach {
            file.appendText("$it \n")
        }

        Log.d(TAG, "Función save(Lista de Modelos) de File en ejecución, se están guardando todas las alertas")

    }

    override fun save(alert: AlertModel) {
        val file = File(activity.filesDir, "alert.txt")
        file.writeText("$alert \n")

        Log.d(TAG, "Función save(Un Modelo) de File en ejecución, se está guardando una alerta como modelo")

    }

    override fun findById(alertId: String): AlertModel? {

        val file = File(activity.filesDir, "alert.txt")//.readLines().mutableListOf<AlertModel>()

        val contentFile = dataSource.findById(alertId)

        file.writeText(contentFile.toString())

        Log.d(TAG, "Función findbyId() de File en ejecución, se está buscando la alerta por id")

        return contentFile
    }

}