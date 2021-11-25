package com.detarco.add_playground.ut01.ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.detarco.add_playground.R
import com.detarco.add_playground.commons.serializer.GsonSerializer
import com.google.gson.Gson
import java.util.*

class Ut01Ex02 : AppCompatActivity() {

    private val TAG = Ut01Ex02::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ut01_ex02)

        val customerFileLocalSource = CustomerFileLocalSource(applicationContext, GsonSerializer(Gson()))
        val invoiceFileLocalSource = InvoiceFileLocalSource(applicationContext, GsonSerializer(Gson()))

        //customerFileLocalSource.save(CustomerModel(1,"Name","Surname"))

        //customerFileLocalSource.save(CustomerModel(2, "nam", "surnam"))
        customerFileLocalSource.update(CustomerModel(2, "name2", "surname2"))



        invoiceFileLocalSource.save(InvoiceModel(3, Date(), CustomerModel(3, "name3", "surname3"), mutableListOf()))


        customerFileLocalSource.fetch()

    }

}