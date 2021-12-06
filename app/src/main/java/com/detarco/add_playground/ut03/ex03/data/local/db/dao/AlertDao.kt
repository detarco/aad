package com.detarco.add_playground.ut03.ex03.data.local.db.dao

import androidx.room.*
import com.detarco.add_playground.ut03.ex03.data.local.db.entity.AlertAndFiles
import com.detarco.add_playground.ut03.ex03.data.local.db.entity.AlertEntity
import com.detarco.add_playground.ut03.ex03.data.local.db.entity.FileEntity

@Dao
interface AlertDao {

    @Transaction
    @Query("SELECT * FROM alerts")
    fun findAll(): List<AlertAndFiles>

    @Transaction
    @Query("SELECT * FROM alerts WHERE id = :alertId")
    fun findById(alertId: String): AlertAndFiles?

    @Insert
    fun insert(alertEntity: AlertEntity, fileEntities: List<FileEntity>)
}