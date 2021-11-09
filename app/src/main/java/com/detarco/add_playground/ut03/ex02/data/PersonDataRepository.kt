package com.detarco.add_playground.ut03.ex02.data

import com.detarco.add_playground.ut03.ex02.domain.PersonModel
import com.detarco.add_playground.ut03.ex02.domain.PersonRepository

class PersonDataRepository (private val personLocalSource: PersonLocalSource) : PersonRepository {

    override fun savePerson(personModel: PersonModel) {
        personLocalSource.save(personModel)
    }

    override fun fetchAll(): List<PersonModel> = personLocalSource.findAll()

}