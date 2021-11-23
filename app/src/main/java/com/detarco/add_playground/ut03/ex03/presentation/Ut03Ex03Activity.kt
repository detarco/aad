package com.detarco.add_playground.ut03.ex03.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.detarco.add_playground.R
import com.detarco.add_playground.ut03.ex03.data.local.db.AlertDbLocalSource
import com.detarco.add_playground.ut03.ex03.data.local.files.AlertFileLocalSource


class Ut03Ex03Activity : AppCompatActivity() {

    private val TAG = Ut03Ex03Activity::class.java.simpleName


    private val viewModel: Ut03Ex03ViewModel by lazy {
        AlertViewModelFactory.build(AlertDbLocalSource(applicationContext))
        //AlertViewModelFactory.build(AlertFileLocalSource(applicationContext as AppCompatActivity))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ut03_ex03)

        //executeQuery()
        //saveLocal()
        //fromDatabaseToSharPref()

        //getAllAlerts()
        //getAlertById()

    }

    private fun getAllAlerts(){
        val alerts = viewModel.getAlerts()

        Log.d(TAG, "$alerts")
        //..Visualizar la información en un LOG.

        // ¿Te atreves con un RecyclerView?
        // No puedo trabajar con ello, la c
    }

    private fun getAlertById(){
        val alertId = "1900673"
        val alerts = viewModel.findAlert(alertId)
        Log.d(TAG, "$alerts")
        // ¿Te atreves con un RecyclerView?
    }

    /**
    private fun executeQuery(){

        Thread{
            repository.saveCustomer(
                CustomerModel(1, "Fer",23,
                    mutableListOf(ClothesModel(2, "Lana"))
                )
            )

            /* Sólo guardaria uno en SharedPreference, hacer cambios?

            repository.saveCustomer(
                CustomerModel(2,"Ser", 22,
                mutableListOf(ClothesModel(3,"Tela")))
            )
            */
            val customers = repository.fetchAll()
            Log.d(TAG,"$customers")

        }.start()

    }

    private fun saveLocal(){
        val customerLocalModel = customerLocalRepository.fetch()

        if (customerLocalModel == null) {
            customerLocalRepository.save(CustomerLocalModel("Fernando Local",23))
            Log.d(TAG, "Se ha creado un fichero con el modelo")
        }else{
            /*
            No sé por qué inicializa un modelo con nombre nulo y edad vacía, necesita mejora
             */
            customerLocalRepository.save(CustomerLocalModel("Fernando Local", 23))
            Log.d(TAG, "Se ha modificado el fichero con el modelo")
        }
        Log.d(TAG, "saveLocal() terminada")

    }

    private fun fromDatabaseToSharPref(){

        val customerLocalModel = customerLocalRepository.fetch()

        if (customerLocalModel != null){

            Thread{

                val customers = repository.fetchAll()
                customers.forEach{
                    customerLocalRepository.save(CustomerLocalModel(it.name, it.age))
                    Log.d(TAG, "El documento sólo guarda el primero: $it");
                }

            }.start()
        }
    }

    */
}