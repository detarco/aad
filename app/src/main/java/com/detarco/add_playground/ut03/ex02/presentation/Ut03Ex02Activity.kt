package com.detarco.add_playground.ut03.ex02.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.detarco.add_playground.R
import com.detarco.add_playground.ut03.ex02.data.PersonDataRepository
import com.detarco.add_playground.ut03.ex02.data.local.PersonLocalSource
import com.detarco.add_playground.ut03.ex02.domain.*

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
            repository.savePerson(
                PersonModel(1, "Fer",23,"1",
                PetModel(2,"Tián", 2),
                mutableListOf(CarModel(3, "Mercedes", "A4")),
                    mutableListOf(JobModel(4, "Alumno")))
            )
            val people = repository.fetchAll()
            Log.d(TAG,"$people")

            //val peopleAndPets = repository.
        }.start()

    }


}