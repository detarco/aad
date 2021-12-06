package com.detarco.add_playground.ut03.ex02.data.local.dao

import androidx.room.*
import com.detarco.add_playground.ut03.ex02.data.*

@Dao
interface PersonDao {

    @Update
    fun update(vararg personEntity: PersonEntity)

    @Delete
    fun delete(vararg personEntity: PersonEntity)

    //@Transaction
    @Query("SELECT * FROM person")
    fun findAll(): List <PersonEntity>
    //fun findAll(): List <PersonEntity>?

    @Query("SELECT * FROM person where id = :personId")
    fun findById(personId: Int): PersonEntity


    @Insert//(onConflict = IGNORE)
    fun insert(personEntity: PersonEntity): Long

    @Transaction
    @Query("SELECT * FROM person")
    fun getPersonAndPet(): List<PersonAndPet>

    @Insert
    fun insertPersonAndPet(
        personEntity: PersonEntity,
        petEntity: PetEntity
    )
    @Insert
    fun insertPersonAndPetAndCars(
        personEntity: PersonEntity,
        petEntity: PetEntity,
        carEntity: List<CarEntity>
    )

    @Transaction
    @Query("SELECT * FROM person")
    fun getPersonAndPetAndCars(): List<PersonAndPetAndCars>

    @Transaction
    @Query("SELECT * FROM person")
    fun getPersonAndPetsAndCarsAndJobs(): List<PersonAndPetAndCarsAndJobs>

    @Insert
    fun insertPersonAndPetsAndCarsAndJobs(
        personEntity: PersonEntity,
        petEntity: PetEntity,
        carEntities: List<CarEntity>,
        jobsEntities: List<JobEntity>,
        joinEntities: List<PersonJobEntity>
    )

}