package com.detarco.add_playground.ut03.ex04.data.local.db.entity

import androidx.room.*
import com.detarco.add_playground.ut03.ex04.domain.CustomerModel
import com.detarco.add_playground.ut03.ex04.domain.InvoiceLinesModel
import com.detarco.add_playground.ut03.ex04.domain.InvoiceModel
import com.detarco.add_playground.ut03.ex04.domain.ProductModel

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

/**
 * Relación 1 a N
 */

@Entity(tableName = "invoice_lines")
data class InvoiceLinesEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "product_id")val productId:Int
){
    fun toModel(
        productEntity: ProductEntity
    ) =
        InvoiceLinesModel(
            id,
            productEntity.toModel()
        )
    companion object{
        fun toEntity(
            invoiceLinesModel: InvoiceLinesModel,
            productId: Int
        ) =
            InvoiceLinesEntity(invoiceLinesModel.id, productId)
    }
}

@Entity(tableName = "invoice")
data class InvoiceEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "date") val date:String,
    @ColumnInfo(name = "customer_id") val customerId:Int,
    @ColumnInfo(name = "invoice_line_id") val invoiceLineId:Int
){
    fun toModel(
        customerEntity: CustomerEntity,
        invoiceLinesEntity: List<InvoiceLinesEntity>
    ) = InvoiceModel(
        id,
        //SpainDate(date).dateToString(date),
        date,
        customerEntity.toModel(),
        mutableListOf()
        //invoiceLinesEntity.map { it.toModel() }.toMutableList()
    )
}

data class InvoiceAndProduct(
        @Embedded val invoiceLinesEntity: InvoiceLinesEntity,

        @Relation(
        parentColumn = "id",
        entityColumn = "product_id"
    )val productEntity: ProductEntity
    ){
    fun toModel() = InvoiceLinesModel(
        invoiceLinesEntity.id,
        productEntity.toModel()
    )
}

data class InvoiceAndCustomerAndProduct(
    @Embedded val invoiceEntity: InvoiceEntity,

    @Relation(
        parentColumn = "customer_id",
        entityColumn = "id",
    ) val customerEntity: CustomerEntity,

    @Relation(
        parentColumn = "invoice_line_id",
        entityColumn = "id",
    ) val invoiceLinesEntities: List<InvoiceLinesEntity>

    ){
    fun toModel() = invoiceEntity.toModel(customerEntity, invoiceLinesEntities)
}
