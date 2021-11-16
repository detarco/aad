package com.detarco.add_playground.ut03.ex03.presentation

import android.content.ContentValues.TAG
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.detarco.add_playground.R
import com.detarco.add_playground.commons.GsonSerializer
import com.detarco.add_playground.ut03.ex03.app.RetrofitApiClient
import com.detarco.add_playground.ut03.ex03.data.CustomerDataRepository
import com.detarco.add_playground.ut03.ex03.data.local.CustomerLocalSource
import com.detarco.add_playground.ut03.ex03.data.local.SharPrefLocalStorage
import com.detarco.add_playground.ut03.ex03.domain.*
import okhttp3.Headers.Companion.toHeaders
import com.google.gson.Gson




class Ut03Ex03Activity : AppCompatActivity() {

    private val TAG = Ut03Ex03Activity::class.java.simpleName
    private lateinit var customerLocalRepository: CustomerLocalRepository

    //RecyclerViewStuff
    private var recyclerView = findViewById<RecyclerView>(R.id.rvAlerts)
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val repository : CustomerRepository by lazy {
        CustomerDataRepository(CustomerLocalSource(applicationContext))
    }

    private val viewModel: Ut03Ex03ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ut03_ex03)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        customerLocalRepository = CustomerLocalRepository(SharPrefLocalStorage(this, GsonSerializer()))

        executeQuery()
        //saveLocal()
        fromDatabaseToSharPref()

        //retrofit2.Retrofit.Builder() //No sé qué estoy haciendo


        //No se crean alerts, cómo hacerlo
        //getAllAlerts()
        //getAlertById()

    }

    private fun getAllAlerts(){
        val alerts = viewModel.getAlerts()

        //..Visualizar la información en un LOG.
        alerts.forEach {
            Log.d(TAG, it.toString())
        }

        // ¿Te atreves con un RecyclerView?
        // No puedo trabajar con ello, la c
    }

    private fun getAlertById(){
        val alertId = "1"
        val alerts = viewModel.findAlert(alertId)
        Log.d(TAG, "$alerts")
        // ¿Te atreves con un RecyclerView?
    }

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

    /*
    */
}