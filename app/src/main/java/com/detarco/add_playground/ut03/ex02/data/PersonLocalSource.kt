package com.detarco.add_playground.ut03.ex02.data

import android.content.Context
import com.detarco.add_playground.ut03.ex02.app.Ut03Ex02DataBase
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

        val people = db.personDao().getPersonAndPets()

        return people?.map { element -> element.toModel() } ?: mutableListOf()

    }

    fun save(personModel: PersonModel) {
        db.personDao().insertPersonAndPet(
            PersonEntity(
                personModel.id,
                personModel.name,
                personModel.age
            ), PetEntity(
                personModel.petModel.id,
                personModel.petModel.name,
                personModel.petModel.age,
                personModel.id
            )
        )
    }

    fun saveWithoutID(personModel: PersonModel) {
        //val personId

    }
}