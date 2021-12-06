package com.detarco.add_playground.ut03.ex04.data.local.db

import android.content.Context
import com.detarco.add_playground.ut03.ex04.app.Ut03Ex04DataBase
import com.detarco.add_playground.ut03.ex04.data.local.db.entity.CustomerEntity
import com.detarco.add_playground.ut03.ex04.data.local.db.entity.InvoiceEntity
import com.detarco.add_playground.ut03.ex04.data.local.db.entity.InvoiceLinesEntity
import com.detarco.add_playground.ut03.ex04.domain.InvoiceModel
import com.detarco.add_playground.ut03.ex04.domain.repository.InvoiceRepository


class InvoiceDBLocalSource(private val appContext: Context) : InvoiceRepository {

    private val db: Ut03Ex04DataBase by lazy {

        Ut03Ex04DataBase.getInstance(appContext)

    }

    init {
        Thread {
            db.clearAllTables()
        }.start()
        Thread.sleep(1000)
    }

    override fun save(invoiceModel: InvoiceModel) {

        db.invoiceDao().insertInvoiceAndCustomerAndLine(

            InvoiceEntity.toEntity(
                invoiceModel,
                invoiceModel.customerModel.id,
                /**
                 * Esto es un parche
                 *
                 * invoiceModel.id
                 */
                //InvoiceAndProduct(invoiceModel.invoiceLinesModel.map { element ->  })
                invoiceLineId = 1
            ),
                //invoiceModel.id),
            CustomerEntity.toEntity(invoiceModel.customerModel),
            InvoiceLinesEntity.toModelList(
                invoiceModel.invoiceLinesModel.map {
                it }
            )
        )

    }


    override fun findAll(): List<InvoiceModel> {
        val invoice = db.invoiceDao().getInvoiceAndCustomer()
        return invoice.map { it.toModel() }
    }

    override fun result(invoiceId: Int): InvoiceModel? {
        TODO("Not yet implemented")
    }

}
