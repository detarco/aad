package com.detarco.add_playground.ut03.ex04.domain

data class CustomerModel(
    val id: Int,
    val name: String,
    val surname: String
    )

data class ProductModel(
    val id: Int,
    val name: String,
    val model: String,
    val price: Double
    )

data class InvoiceLinesModel(
    val id: Int,
    val product: ProductModel
    )

data class InvoiceModel(
    val id: Int,
    /**
     * Cambio para poder guardar la fecha como string
     * date da problemas porque recoge nulo
     */
    val date: String,
    val customerModel: CustomerModel,
    val invoiceLinesModel: List<InvoiceLinesModel>
    )