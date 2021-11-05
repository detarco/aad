package com.detarco.add_playground.ut03.ex02.ex02_1.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.detarco.add_playground.ut03.ex02.ex02_1.domain.PersonModel

@Entity(tableName = "person")
data class PersonEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int
){
    fun toModel(): PersonModel = PersonModel(id, name, age, null)
}