package com.detarco.add_playground.ut03.ex04.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.detarco.add_playground.R
import com.detarco.add_playground.ut03.ex04.data.CustomerDataRepository
import com.detarco.add_playground.ut03.ex04.data.local.db.CustomerDBLocalSource
import com.detarco.add_playground.ut03.ex04.domain.CustomerModel
import com.detarco.add_playground.ut03.ex04.domain.CustomerRepository

class Ut03Ex04Activity : AppCompatActivity() {

    private val TAG = Ut03Ex04Activity::class.java.simpleName

    private val customerRepository : CustomerRepository by lazy{
        CustomerDataRepository(CustomerDBLocalSource(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ut03_ex04)
        //executeQuery()

        this.apply {
            this.executeQuery()
        }

    }

    private fun executeQuery(){
        Thread{
            customerRepository.apply {
                this.saveCustomer(
                    CustomerModel(
                        1,
                        "Fer",
                        "G.M."
                    )
                )
            }
            customerRepository.fetchAllCustomers()
        }.start()
    }
}