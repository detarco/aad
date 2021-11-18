package com.detarco.add_playground.ut03.ex03.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.detarco.add_playground.ut03.ex03.data.local.entity.FilesEntity

@Dao
interface FileDao {
    @Insert
    fun insert(filesEntity: List<FilesEntity>): List<Long>
}