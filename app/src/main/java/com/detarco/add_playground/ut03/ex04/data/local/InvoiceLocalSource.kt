package com.detarco.add_playground.ut03.ex04.data.local

import com.detarco.add_playground.ut03.ex04.domain.InvoiceModel

interface InvoiceLocalSource {

    fun save(invoice: InvoiceModel)

    fun result(invoiceId : Int): InvoiceModel?
}