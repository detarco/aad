package com.detarco.add_playground.ut03.ex04.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CustomerDao {

    @Transaction
    @Query("SELECT * FROM alerts")
    fun findAll(): List<AlertAndFiles>

    @Transaction
    @Query("SELECT * FROM customers WHERE id = :customerId")
    fun findById(customerId: String): AlertAndFiles?

    @Insert
    fun insert(alertEntity: AlertEntity)

    @Update
    fun update(alertEntity: AlertEntity)
}