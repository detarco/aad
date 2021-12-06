package com.detarco.add_playground.ut03.ex02.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.detarco.add_playground.ut03.ex02.data.local.entity.CarEntity

@Dao
interface CarDao {

    @Query("SELECT * FROM cars")
    fun findAll(): List <CarEntity>

    @Insert
    fun insert(carEntity: CarEntity)

}