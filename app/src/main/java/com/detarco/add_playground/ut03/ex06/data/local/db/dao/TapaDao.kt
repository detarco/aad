package com.detarco.add_playground.ut03.ex06.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.detarco.add_playground.ut03.ex06.data.local.db.entities.BarEntity
import com.detarco.add_playground.ut03.ex06.data.local.db.entities.TapaAndBar
import com.detarco.add_playground.ut03.ex06.data.local.db.entities.TapaEntity

@Dao
interface TapaDao {


    @Transaction
    @Query("SELECT * FROM tapas")
    fun findAllTapas(): List<TapaAndBar>

    @Transaction
    @Query("SELECT * FROM tapas WHERE id= :tapaId LIMIT 1")
    fun findTapaByID(tapaId:String): TapaAndBar

    @Insert
    fun saveTapa(tapaEntity: TapaEntity,
                 barEntity: BarEntity)



    /**
    @Insert
    fun saveTapas(
        tapaEntity: TapaEntity,
        barEntity: BarEntity
        )
    */
}