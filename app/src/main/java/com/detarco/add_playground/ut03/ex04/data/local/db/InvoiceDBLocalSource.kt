package com.detarco.add_playground.ut03.ex04.data.local.db

import android.content.Context
import com.detarco.add_playground.ut03.ex04.app.Ut03Ex04DataBase
import com.detarco.add_playground.ut03.ex04.data.local.InvoiceLocalSource
import com.detarco.add_playground.ut03.ex04.domain.CustomerModel
import com.detarco.add_playground.ut03.ex04.domain.InvoiceModel

class InvoiceDBLocalSource(private val appContext: Context) : InvoiceLocalSource {

    private val db: Ut03Ex04DataBase by lazy {

        Ut03Ex04DataBase.getInstance(appContext)

    }

    override fun save(invoice: InvoiceModel) {
        TODO("Not yet implemented")
    }

    override fun result(invoiceId: Int): InvoiceModel? {
        TODO("Not yet implemented")
    }

}