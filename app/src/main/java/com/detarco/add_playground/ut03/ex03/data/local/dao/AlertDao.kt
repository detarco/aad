package com.detarco.add_playground.ut03.ex03.data.local.dao

import androidx.room.*
import com.detarco.add_playground.ut03.ex03.data.local.entity.AlertEntity

@Dao
interface AlertDao {

    @Update
    fun update(vararg alertEntity: AlertEntity)

    @Delete
    fun delete(vararg alertEntity: AlertEntity)

    @Insert//Insert
    fun save(alertEntity: AlertEntity): Long

    @Query("SELECT * FROM alerts WHERE id= :alertId")
    fun findById(alertId: String): AlertEntity

    @Query("SELECT * FROM alerts")
    fun findAll(): List<AlertEntity>

}