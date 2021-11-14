package com.detarco.add_playground.ut03.ex03.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.detarco.add_playground.ut03.ex03.data.local.entity.ClothesEntity

@Dao
interface ClothesDao {

    @Query("SELECT * FROM clothes")
    fun findAll(): List <ClothesEntity>

    @Insert
    fun insert(carEntity: ClothesEntity)

}