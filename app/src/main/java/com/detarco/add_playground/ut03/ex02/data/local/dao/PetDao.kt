package com.detarco.add_playground.ut03.ex02.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.detarco.add_playground.ut03.ex02.data.PetEntity

@Dao
interface PetDao {

    @Query("SELECT * FROM pet")
    fun findAll(): List <PetEntity>

    @Insert
    fun insert(petEntity: PetEntity)

}