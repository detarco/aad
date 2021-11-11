package com.detarco.add_playground.ut03.ex02.data

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE

@Dao
interface PersonDao {

    //@Transaction
    @Query("SELECT * FROM person")
    fun findAll(): List <PersonEntity>
    //fun findAll(): List <PersonEntity>?

    @Query("SELECT * FROM person where id = :personId")
    fun findById(personId: Int): PersonEntity


    @Insert//(onConflict = IGNORE)
    fun insert(personEntity: PersonEntity): Long

    @Insert
    fun insertPersonAndPet(
        personEntity: PersonEntity,
        petEntity: PetEntity
    )

    @Update
    fun update(vararg personEntity: PersonEntity)

    @Transaction
    @Query("SELECT * FROM person")
    fun getPersonAndPets(): List<PersonAndPet>

    @Transaction
    @Query("SELECT * FROM person")
    fun getPersonAndPetsAndCars(): List<PersonAndPetAndCars>

}