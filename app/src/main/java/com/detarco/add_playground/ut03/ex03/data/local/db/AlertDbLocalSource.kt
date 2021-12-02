package com.detarco.add_playground.ut03.ex03.data.local.db

import android.content.Context
import com.detarco.add_playground.ut03.ex03.app.db.Ut03Ex03DataBase
import com.detarco.add_playground.ut03.ex03_b.ex03.data.local.AlertLocalSource
import com.detarco.add_playground.ut03.ex03.data.local.db.entity.AlertEntity
import com.detarco.add_playground.ut03.ex03.data.local.db.entity.FileEntity
import com.detarco.add_playground.ut03.ex03_v2.domain.AlertModel

class AlertDbLocalSource(private val appContext: Context) : AlertLocalSource {

    private val db: Ut03Ex03DataBase by lazy {

        Ut03Ex03DataBase.getInstance(appContext)

    }

    override fun findAll(): List<AlertModel> {

        val alertAndFiles = db.alertDao().findAll()

        return alertAndFiles.map { element -> element.toModel() }

    }

    override fun save(alerts: List<AlertModel>) {

        alerts.forEach { alertModel ->
            save(alertModel)
        }

    }

    override fun save(alert: AlertModel) {

        db.alertDao().insert(AlertEntity.toEntity(alert), alert.files.map { fileModel -> FileEntity.toEntity(alert.id, fileModel) })

    }

    override fun findById(alertId: String): AlertModel? {

        return db.alertDao().findById(alertId)?.toModel()

    }

}