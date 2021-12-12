package com.detarco.add_playground.ut03.ex06.data.local.db.dao

import androidx.room.Dao

@Dao
interface CompetitionDao {
/**
    @Transaction
    @Query("SELECT * from competition")
    fun findAllCompetition(): List<TapaAndCompetition>

    @Transaction
    @Query("SELECT * FROM competition where id= :competitionId LIMIT 1")
    fun findCompetitionById(competitionId : String) : TapaAndCompetition
*/
    /**
    @Insert
    fun saveCompetition(
        competitionEntity: CompetitionEntity,
        tapasEntities: List<TapaAndCompetition>
    )
    */
}
