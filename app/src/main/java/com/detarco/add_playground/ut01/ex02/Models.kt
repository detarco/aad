package com.detarco.add_playground.ut01.ex02

import java.util.*

data class CustomerModel(val id: Int, val name: String, val surname: String)
data class ProductModel(val id: Int, val name: String, val model: String, val price: Double)
data class InvoiceLinesModel(val id: Int, val product: ProductModel)
data class InvoiceModel(val id: Int,
                        val date: Date,
                        val customerModel: CustomerModel,
                        val invoiceLinesModel: List<InvoiceLinesModel>)
