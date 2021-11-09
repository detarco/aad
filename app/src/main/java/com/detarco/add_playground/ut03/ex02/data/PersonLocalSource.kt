package com.detarco.add_playground.ut03.ex02.data

import android.content.Context
import com.detarco.add_playground.ut03.ex02.app.Ut03Ex02DataBase
import com.detarco.add_playground.ut03.ex02.domain.PersonModel

class PersonLocalSource(applicationContext: Context) {

    private val db = Ut03Ex02DataBase.getInstance(applicationContext)

    fun findAll(): List<PersonModel>{
        val people = db.personDao().findAll()
        return people.map { element -> element.toModel() }
    }

    fun save(personalModel: PersonModel){

        db.personDao().insertPersonAndPet(
            PersonEntity(
                personalModel.id,
                personalModel.name,
                personalModel.age
            ), PetEntity(
                personalModel.petModel.id,
                personalModel.petModel.name,
                personalModel.petModel.age,
                personalModel.id
            )
        )

        db.personDao().insert(
            PersonEntity(
            personalModel.id,
            personalModel.name,
            personalModel.age
            )
        )
    }
}