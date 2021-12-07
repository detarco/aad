package com.detarco.add_playground.ut03.ex06.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.detarco.add_playground.ut03.ex06.data.local.db.entities.BarEntity

@Dao
interface BarDao {

    @Query("SELECT * FROM bares")
    fun findAllBares(): Result<List<BarEntity>>

    @Query("SELECT * FROM bares WHERE id= :barId LIMIT 1")
    fun findBarByID(barId:String): Result<BarEntity>

    @Insert
    fun saveBar(barEntity: BarEntity): Result<Boolean>

    @Insert
    fun saveBares(barEntity: List<BarEntity>): Result<Boolean>
}