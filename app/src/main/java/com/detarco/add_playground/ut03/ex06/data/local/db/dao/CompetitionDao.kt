package com.detarco.add_playground.ut03.ex06.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.detarco.add_playground.ut03.ex06.data.local.db.entities.CompetitionEntity
import com.detarco.add_playground.ut03.ex06.data.local.db.entities.OneBarOneTapaEntity
import com.detarco.add_playground.ut03.ex06.data.local.db.entities.OneBarWithTapaCompetition

@Dao
interface CompetitionDao {

    @Query("SELECT * from competition")
    fun findAllCompetition(): Result<List<OneBarWithTapaCompetition>>

    @Query("SELECT * FROM competition where id= :competitionId LIMIT 1")
    fun findCompetitionById(competitionId : String) : Result<OneBarWithTapaCompetition>

    @Insert
    fun saveCompetition(
        competitionEntity: CompetitionEntity,
        tapasEntities: List<OneBarOneTapaEntity>
    ):Result<Boolean>
}