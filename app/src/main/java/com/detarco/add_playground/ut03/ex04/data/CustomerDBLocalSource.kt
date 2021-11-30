package com.detarco.add_playground.ut03.ex04.data

import android.content.Context
import com.detarco.add_playground.ut03.ex03.app.db.Ut03Ex03DataBase
import com.detarco.add_playground.ut03.ex03.data.local.AlertLocalSource
import com.detarco.add_playground.ut03.ex03.data.local.db.entity.AlertEntity
import com.detarco.add_playground.ut03.ex03.data.local.db.entity.FileEntity
import com.detarco.add_playground.ut03.ex03.domain.AlertModel
import com.detarco.add_playground.ut03.ex04.app.Ut03Ex04DataBase
import com.detarco.add_playground.ut03.ex04.domain.CustomerModel

class CustomerDBLocalSource(private val appContext: Context) : CustomerLocalSource {

    private val db: Ut03Ex03DataBase by lazy {

        Ut03Ex04DataBase.getInstance(appContext)

    }

    override fun findAll(): List<CustomerModel> {

        val alertAndFiles = db.alertDao().findAll()

        return alertAndFiles.map { element -> element.toModel() }

    }

    override fun save(alerts: List<CustomerModel>) {

        alerts.forEach { alertModel ->
            save(alertModel)
        }

    }

    override fun save(alert: CustomerModel) {

        db.alertDao().insert(AlertEntity.toEntity(alert), alert.files.map { fileModel -> FileEntity.toEntity(alert.id, fileModel) })

    }

    override fun findById(alertId: String): CustomerModel? {

        return db.alertDao().findById(alertId)?.toModel()

    }

}