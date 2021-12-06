package com.detarco.add_playground.ut03.ex04.domain.repository

import com.detarco.add_playground.ut03.ex04.domain.InvoiceModel

interface InvoiceRepository {

    fun save(invoiceModel: InvoiceModel)

    fun findAll(): List<InvoiceModel>

    fun result(invoiceId : Int): InvoiceModel?
}