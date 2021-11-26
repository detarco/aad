package com.detarco.add_playground.ut03.ex04.data.db

import androidx.room.*
import com.detarco.add_playground.ut03.ex04.domain.CustomerModel
import com.detarco.add_playground.ut03.ex04.domain.InvoiceLinesModel
import com.detarco.add_playground.ut03.ex04.domain.InvoiceModel
import com.detarco.add_playground.ut03.ex04.domain.ProductModel
import java.util.*

@Entity(tableName = "customers")
data class CustomerEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "surname") val surname:String
){
    fun toModel(): CustomerModel = CustomerModel(id, name, surname)

    companion object{
        fun toEntity(customerModel: CustomerModel) =
            CustomerEntity(customerModel.id, customerModel.name, customerModel.surname)
    }
}

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "model") val model:String,
    @ColumnInfo(name = "price") val price:Double
){
    fun toModel(): ProductModel = ProductModel(id, name, model, price)

    companion object{
        fun toEntity(productModel: ProductModel) =
            ProductEntity(productModel.id, productModel.name, productModel.model, productModel.price)
    }
}

@Entity(tableName = "invoice")
data class InvoiceEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "date") val date: Date
){
    fun toModel(
        customerEntity: CustomerEntity,
        invoiceLinesEntity: List<InvoiceLinesEntity>
    ) =
        InvoiceModel(
            id,
            date,
            customerEntity.toModel(),
            invoiceLinesEntity.map{it.toModel()}.toMutableList()
        )
}

@Entity(tableName = "invoice_lines")
data class InvoiceLinesEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id:Int
){
    fun toModel(
        productEntity: ProductEntity
    ) =
        InvoiceLinesModel(
            id,
            date,
            customerEntity.toModel(),
            invoiceLinesEntity.map{it.toModel()}.toMutableList()
        )
}

data class InvoiceAndProduct(
    @Embedded val customerEntity: CustomerEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "product_id"
    )val productEntity: ProductEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = ""
    )val invoiceEntity:
