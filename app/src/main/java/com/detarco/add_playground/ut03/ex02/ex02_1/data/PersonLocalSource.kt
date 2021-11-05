package com.detarco.add_playground.ut03.ex02.ex02_1.data

import android.content.Context
import com.detarco.add_playground.ut03.ex02.ex02_1.app.Ut03Ex02DataBase
import com.detarco.add_playground.ut03.ex02.ex02_1.domain.PersonModel

class PersonLocalSource(applicationContext: Context) {

    private val db = Ut03Ex02DataBase.getInstance(applicationContext)

    fun findAll(): List<PersonModel>{
        val people = db.personDao().findAll()
        return people.map { element -> element.toModel() }
    }
}