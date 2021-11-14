package com.detarco.add_playground.ut03.ex03.presentation

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.detarco.add_playground.R
import com.detarco.add_playground.ut03.ex03.data.CustomerDataRepository
import com.detarco.add_playground.ut03.ex03.data.local.CustomerLocalSource
import com.detarco.add_playground.ut03.ex03.domain.ClothesModel
import com.detarco.add_playground.ut03.ex03.domain.CustomerModel
import com.detarco.add_playground.ut03.ex03.domain.CustomerRepository

class Ut03Ex03Activity : AppCompatActivity() {

    private val repository : CustomerRepository by lazy {
        CustomerDataRepository(CustomerLocalSource(applicationContext))
    }

    private val viewModel: Ut03Ex03ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ut03_ex03)

        executeQuery()

    }

    private fun getAllAlerts(){
        val alerts = viewModel.getAlerts()
        //..Visualizar la información en un LOG.
        // ¿Te atreves con un RecyclerView?
    }

    private fun getAlertById(){
        val alertId = ""
        val alerts = viewModel.findAlert(alertId)
        // ¿Te atreves con un RecyclerView?
    }

    private fun executeQuery(){

        Thread{
            repository.saveCustomer(
                CustomerModel(1, "Fer",23,
                    mutableListOf(ClothesModel(2, "Lana"))
                )
            )
            val customers = repository.fetchAll()
            Log.d(TAG,"$customers")

        }.start()

    }
}