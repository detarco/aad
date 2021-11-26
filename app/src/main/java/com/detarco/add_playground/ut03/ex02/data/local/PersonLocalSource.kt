package com.detarco.add_playground.ut03.ex02.data.local

import android.content.Context
import com.detarco.add_playground.ut03.ex02.app.Ut03Ex02DataBase
import com.detarco.add_playground.ut03.ex02.data.local.entity.*
import com.detarco.add_playground.ut03.ex02.domain.PersonModel

class PersonLocalSource(applicationContext: Context) {

    private val db = Ut03Ex02DataBase.getInstance(applicationContext)

    init {
        Thread {
            db.clearAllTables()
        }.start()
        Thread.sleep(1000)
    }

    fun findAll(): List<PersonModel> {

        val personAndPetAndCarsAndJobs = db.personDao().getPersonAndPetsAndCarsAndJobs()

        return personAndPetAndCarsAndJobs.map { element -> element.toModel() }

    }

    fun save(personModel: PersonModel) {
        db.personDao().insertPersonAndPetsAndCarsAndJobs(
            PersonEntity.toEntity(personModel),
            PetEntity.toEntity(personModel.petModel, personModel.id),
            CarEntity.toEntity(personModel.carModel, personModel.id),
            JobEntity.toEntity(personModel.jobModel),
            PersonJobEntity.toEntity(personModel.id, personModel.jobModel.map { it.id })
            )
    }

    fun saveWithoutID(personModel: PersonModel) {
        TODO()
    }
}