package com.detarco.add_playground.ut02.ex04.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.detarco.add_playground.R
import com.detarco.add_playground.commons.serializer.GsonSerializer
import com.detarco.add_playground.ut02.ex04.data.CustomerSharPrefLocalSource
import com.detarco.add_playground.ut02.ex04.data.InvoiceSharPrefLocalSource
import com.detarco.add_playground.ut02.ex04.domain.CustomerModel
import com.detarco.add_playground.ut02.ex04.domain.InvoiceLinesModel
import com.detarco.add_playground.ut02.ex04.domain.InvoiceModel
import com.detarco.add_playground.ut02.ex04.domain.ProductModel
import com.google.gson.Gson

class Ut02Ex04 : AppCompatActivity() {

    private val TAG: String = Ut02Ex04::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ut02_ex04)

        saveCustomers()
        saveInvoices()
    }

    private fun saveCustomers(){
        val customerDataSource = CustomerSharPrefLocalSource(this, GsonSerializer(Gson()))
        customerDataSource.save(customers)
        /**
        val customerList = customerDataSource.fetch()
        customerList.map {
            Log.d(TAG, "$it")
        }
        */
    }
    private fun saveInvoices(){
        val invoiceDataSource = InvoiceSharPrefLocalSource(this, GsonSerializer(Gson()))
        invoiceDataSource.save(invoices)
        /**
        val invoiceList = invoiceDataSource.fetch()
        invoiceList.map {
            Log.d(TAG, "$it")
        }
        */
    }

    private val customers : List<CustomerModel> = mutableListOf(
        CustomerModel(
            1, "Fer", "G.M."
        ),
        CustomerModel(
            2, "Ser", "G.M."
        )
    )
    private val invoices: InvoiceModel = InvoiceModel(
        1,
        "03/12/21",
        CustomerModel(
            3, "Pedro", "Museros"
        ),
        mutableListOf(
            InvoiceLinesModel(
                1, ProductModel(1, "Mazinger Z", "Figura", 9.99)
            ),
            InvoiceLinesModel(
                2, ProductModel(2, "Cómic Batman", "Cómic", 21.99)
            )
        )

    )
}
