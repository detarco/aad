package com.detarco.add_playground.ut02.ex04.domain


data class CustomerModel(val id: Int, val name: String, val surname: String)
data class ProductModel(val id: Int, val name: String, val model: String, val price: Double)
data class InvoiceLinesModel(val id: Int, val product: ProductModel)
data class InvoiceModel(val id: Int,
                        /**
                         * Cambio a string para poder trabajar con ello
                         * debo mirar cómo declarar Date
                         */
                        val date: String,
                        val customerModel: CustomerModel,
                        val invoiceLinesModel: List<InvoiceLinesModel>)