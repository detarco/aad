package com.detarco.add_playground.ut03.ex02.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.detarco.add_playground.ut03.ex02.data.local.entity.JobEntity

@Dao
interface JobsDao {

    @Query("SELECT * FROM jobs")
    fun findAll(): List <JobEntity>

    @Insert
    fun insert(jobEntity: JobEntity)

}