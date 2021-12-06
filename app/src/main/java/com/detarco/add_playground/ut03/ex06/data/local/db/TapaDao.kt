package com.detarco.add_playground.ut03.ex06.data.local.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface TapaDao {

    @Transaction
    @Query("SELECT * FROM tapas")
}