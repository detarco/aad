package com.detarco.add_playground.ut03.ex03.data.local.db

import android.content.Context
import com.detarco.add_playground.ut03.ex03.app.db.Ut03Ex03DataBase
import com.detarco.add_playground.ut03.ex03.data.local.LocalModel
import com.detarco.add_playground.ut03.ex03.data.local.LocalStorage
import com.detarco.add_playground.ut03.ex03.data.local.db.entity.AlertEntity


class DbLocalSource2<T: LocalModel>(private val context: Context) : LocalStorage<T>{

    init {
        Thread {
            db.clearAllTables()
        }.start()
        Thread.sleep(1000)
    }

    private val db: Ut03Ex03DataBase by lazy {
        Ut03Ex03DataBase.getInstance(context)
    }

    override fun save(models: List<T>) {
        db.runInTransaction {
            val alertId = db.alertDao().save(AlertEntity.toEntity(alertModel))
            db.fileDao().insert(FilesEntity.toEntity(alertModel.filesModel, alertId.toString()))
        }
    }

    override fun findById(alertId: String): T {
        return db.alertDao().findById(alertId)
        //return
    }

    override fun findAll(): List<T> {
        //return db.alertDao().findAll()

        val alertAndFiles = db.alertDao().findAll()

        return alertAndFiles.map { element -> element.toModel() }

    }


}