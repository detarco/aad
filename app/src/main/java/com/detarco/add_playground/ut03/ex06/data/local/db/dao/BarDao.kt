package com.detarco.add_playground.ut03.ex06.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.detarco.add_playground.ut03.ex06.data.local.db.entities.BarEntity
import com.detarco.add_playground.ut03.ex06.domain.BarModel

@Dao
interface BarDao {

    @Query("SELECT * FROM bares")
    fun findAllBares(): List<BarModel>

    @Query("SELECT * FROM bares WHERE id= :barId LIMIT 1")
    fun findBarByID(barId:String): BarModel


    @Insert
    fun save(barEntity: BarEntity)

}