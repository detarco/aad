package com.detarco.add_playground.ut03.ex04.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.detarco.add_playground.R
import com.detarco.add_playground.ut03.ex04.domain.repository.CustomerRepository
import com.detarco.add_playground.ut03.ex04.data.local.db.CustomerDBLocalSource
import com.detarco.add_playground.ut03.ex04.data.local.db.InvoiceDBLocalSource
import com.detarco.add_playground.ut03.ex04.data.local.db.ProductDBLocalSource
import com.detarco.add_playground.ut03.ex04.domain.CustomerModel
import com.detarco.add_playground.ut03.ex04.domain.InvoiceLinesModel
import com.detarco.add_playground.ut03.ex04.domain.InvoiceModel
import com.detarco.add_playground.ut03.ex04.domain.ProductModel
import com.detarco.add_playground.ut03.ex04.domain.repository.InvoiceRepository
import com.detarco.add_playground.ut03.ex04.domain.repository.ProductRepository

class Ut03Ex04Activity : AppCompatActivity() {

    private val TAG = Ut03Ex04Activity::class.java.simpleName

    private val customerRepository : CustomerRepository by lazy {
        CustomerDBLocalSource(applicationContext)
    }
    private val productRepository : ProductRepository by lazy {
        ProductDBLocalSource(applicationContext)
    }
    private val invoiceRepository : InvoiceRepository by lazy {
        InvoiceDBLocalSource(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ut03_ex04)
        //executeQuery()

        this.apply {
            this.executeQueryCustomer()

            this.executeQueryProduct()

            this.executeQueryInvoice()

        }
    }

    private fun executeQueryCustomer(){
        Thread{
            customerRepository.apply {
                this.save(
                    CustomerModel(
                        1,
                        "Fer",
                        "G.M."
                    )
                )
                this.save(
                    CustomerModel(3, "Pedro", "Museros")
                )
                this.delete(3)
                this.findById(1)
            }
            customerRepository.findAll()
        }.start()
    }

    private fun executeQueryProduct(){
        Thread{
            productRepository.apply {
                this.save(
                    ProductModel(
                        4,
                        "Mazinger Z",
                        "Alpha",
                        9.99
                    )
                )
            }
            productRepository.findAll()
        }.start()
    }
    private fun executeQueryInvoice(){
        Thread{
            invoiceRepository.apply {



                invoiceRepository.save(
                    InvoiceModel(1, "01/03/1999",
                        CustomerModel(2, "Ser", "G.M."),
                        mutableListOf<InvoiceLinesModel>(
                            InvoiceLinesModel(
                                1,
                                ProductModel(2, "Subaru","Rem",9.99)
                            )
                        )
                    )
                )
            }
            val invoices = invoiceRepository.findAll()
            Log.d(TAG, "$invoices")
        }.start()
    }
}