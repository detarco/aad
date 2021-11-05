package com.detarco.add_playground.ut03.ex02.ex02_1.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    fun findAll(): List <PersonEntity>

}