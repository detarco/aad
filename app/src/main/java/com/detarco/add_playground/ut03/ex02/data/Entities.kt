package com.detarco.add_playground.ut03.ex02.data

import androidx.room.*
import com.detarco.add_playground.ut03.ex02.domain.PersonModel
import com.detarco.add_playground.ut03.ex02.domain.PetModel

@Entity(tableName = "person")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int
){
    //Se puede obtener información de un fichero a través de esta función aparte del room
    fun toModel(): PersonModel = PersonModel(id, name, age, null, PetModel(id, name, age))
}

@Entity(tableName = "pet")
data class PetEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "person_id") val personId: Int
){
    fun toModel(): PetModel = PetModel(id, name, age)
}

data class PersonAndPet(
    //Tabla padre
    @Embedded val personEntity: PersonEntity,
    @Relation(
        parentColumn = "id",    //Atributo padre
        entityColumn = "person_id", //Crear atributo en la
    ) val petEntity: PetEntity
){
    fun toModel() = PersonModel(
        personEntity.id,
        personEntity.name,
        personEntity.age,
        "",
        PetModel(petEntity.id, petEntity.name, petEntity.age)
    )
}