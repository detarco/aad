package com.detarco.add_playground.ut03.ex06.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.detarco.add_playground.ut03.ex06.data.local.db.entities.OneBarOneTapaEntity
import com.detarco.add_playground.ut03.ex06.data.local.db.entities.TapaEntity
import com.detarco.add_playground.ut03.ex06.domain.TapaModel

@Dao
interface TapaDao {

    /**
     * Uso del TapaEntity?
     */


    @Transaction
    @Query("SELECT * FROM tapas")
    fun findAllTapas(): List<TapaEntity>

    @Query("SELECT * FROM tapas WHERE id= :tapaId LIMIT 1")
    fun findTapaByID(tapaId:String): OneBarOneTapaEntity

    @Insert
    fun saveTapa(tapaEntity: TapaEntity)

    @Insert
    fun saveTapas(tapaEntity: List<TapaEntity>)

}