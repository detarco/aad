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
    //@ColumnInfo(name = "product_model") val prodModel: ProductModel
    @ColumnInfo(name = "product_model") val prodModel: String,
    @ColumnInfo(name = "product_id") val productId:Int
){
    fun toModel(
        productEntity: ProductEntity
    ) =
        InvoiceLinesModel(
            id,
            productEntity.toModel()
        )
    companion object{
        fun toEntity(invoiceLinesModel: List<InvoiceLinesModel>, prodModel: String, productId:Int) =
            invoiceLinesModel.map {
                InvoiceLinesEntity(it.id, prodModel,productId)
            }

        fun toModelList(invoiceLinesModel: List<InvoiceLinesModel>): List<InvoiceLinesEntity> {
            return invoiceLinesModel.map { it -> InvoiceLinesEntity(it.id, prodModel = "prod",1) }
        }
        /**

        fun toEntity(invoiceLinesModel: List<InvoiceLinesModel>) =
            invoiceLinesModel.map {
                InvoiceLinesEntity(it.id, it.product)
            }
         */

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
        productEntity: ProductEntity,
        invoiceLinesEntity: List<InvoiceLinesEntity>
    ) =
        InvoiceModel(
        id,
        date,
        customerEntity.toModel(),
        invoiceLinesEntity.map{it.toModel(productEntity)}.toMutableList()
    )
    companion object{

        fun toEntity(invoiceModel: InvoiceModel, customerId: Int, invoiceLineId: Int) =
            InvoiceEntity(invoiceModel.id, invoiceModel.date, customerId, invoiceLineId)
    }
}

data class LinesAndProducts(
    @Embedded val invoiceLinesEntity: InvoiceLinesEntity,

    @Relation(
        parentColumn = "product_model",
        entityColumn = "model",
    ) val productEntity: ProductEntity,
){
 fun toModel() = InvoiceLinesModel(
     invoiceLinesEntity.id,
     ProductModel(
         productEntity.id,
         productEntity.name,
         productEntity.model,
         productEntity.price
         )
 )
}

data class InvoiceAndCustomer(
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
    fun toModel() = InvoiceModel(
        invoiceEntity.id,
        invoiceEntity.date,
        CustomerModel(
            customerEntity.id,
            customerEntity.name,
            customerEntity.surname
        ),
        mutableListOf()
    )
}
