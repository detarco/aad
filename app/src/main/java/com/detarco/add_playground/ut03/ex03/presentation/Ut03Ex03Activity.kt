package com.detarco.add_playground.ut03.ex03.presentation

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.detarco.add_playground.R
import com.detarco.add_playground.commons.GsonSerializer
import com.detarco.add_playground.ut03.ex03.data.CustomerDataRepository
import com.detarco.add_playground.ut03.ex03.data.local.CustomerLocalSource
import com.detarco.add_playground.ut03.ex03.data.local.SharPrefLocalStorage
import com.detarco.add_playground.ut03.ex03.domain.*
import okhttp3.Headers.Companion.toHeaders
import com.google.gson.Gson




class Ut03Ex03Activity : AppCompatActivity() {

    private val TAG = Ut03Ex03Activity::class.java.simpleName
    private lateinit var customerLocalRepository: CustomerLocalRepository

    private val repository : CustomerRepository by lazy {
        CustomerDataRepository(CustomerLocalSource(applicationContext))
    }

    private val viewModel: Ut03Ex03ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ut03_ex03)

        customerLocalRepository = CustomerLocalRepository(SharPrefLocalStorage(this, GsonSerializer()))

        executeQuery()
        //saveLocal()
        localOrRemote()

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

    /*
    Crashea la aplicación, requiere arreglos
 */

    private fun localOrRemote(){

        val customerLocalModel = customerLocalRepository.fetch()

        if (customerLocalModel != null){

            Thread{

                val customers = repository.fetchAll()
                customers.forEach{
                    customerLocalRepository.save(CustomerLocalModel(it.name, it.age))
                    Log.d(TAG, "El documento debería guardar $it");
                }

            }.start()
        }
    }

    /*
    */
}