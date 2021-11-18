package com.detarco.add_playground.ut03.ex03.data.local

import android.content.Context
import com.detarco.add_playground.ut03.ex03.app.db.Ut03Ex03DataBase
import com.detarco.add_playground.ut03.ex03.data.local.entity.AlertEntity
import com.detarco.add_playground.ut03.ex03.data.local.entity.FilesEntity

class DbLocalStorage<T: LocalModel>(private val context: Context) : LocalStorage<T>{

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