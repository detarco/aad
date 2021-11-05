package com.detarco.add_playground.ut03.ex02.ex02_1.data

import com.detarco.add_playground.ut03.ex02.ex02_1.domain.PersonModel
import com.detarco.add_playground.ut03.ex02.ex02_1.domain.PersonRepository

class PersonDataRepository (private val personLocalSource: PersonLocalSource) : PersonRepository {

    override fun savePerson(personModel: PersonModel) {
        TODO("Not yet implemented")
    }

}