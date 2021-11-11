package com.detarco.add_playground.ut03.ex02.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.detarco.add_playground.R
import com.detarco.add_playground.ut03.ex02.data.PersonDataRepository
import com.detarco.add_playground.ut03.ex02.data.PersonLocalSource
import com.detarco.add_playground.ut03.ex02.domain.CarModel
import com.detarco.add_playground.ut03.ex02.domain.PersonModel
import com.detarco.add_playground.ut03.ex02.domain.PersonRepository
import com.detarco.add_playground.ut03.ex02.domain.PetModel

class Ut03Ex02Activity : AppCompatActivity() {

    private val TAG = Ut03Ex02Activity::class.java.simpleName

    //private lateinit var demo : String

    private val repository : PersonRepository by lazy {
        PersonDataRepository(PersonLocalSource(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ut03ex02)

        executeQuery()

    }

    private fun executeQuery(){

        Thread{
            repository.savePerson(PersonModel(1, "Name01",1,"1", PetModel(1,"Tián", 2), mutableListOf() ))
            val people = repository.fetchAll()
            Log.d(TAG,"$people")

            //val peopleAndPets = repository.
        }.start()

    }


}