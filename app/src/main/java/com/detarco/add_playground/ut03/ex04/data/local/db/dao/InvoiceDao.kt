package com.detarco.add_playground.ut03.ex04.data.local.db.dao

import androidx.room.*
import com.detarco.add_playground.ut03.ex04.data.local.db.entity.*

@Dao
interface InvoiceDao {

    @Update
    fun update(vararg invoiceEntity: InvoiceEntity)

    @Delete
    fun delete(vararg invoiceEntity: InvoiceEntity)

    @Query("Select * FROM invoice")
    fun findAll(): List <InvoiceEntity>

    @Insert
    fun insert(invoiceEntity: InvoiceEntity)

    @Insert
    fun insertLinesAndProducts(
        invoiceLinesEntity: InvoiceLinesEntity,
        productEntity: ProductEntity
    )

    @Transaction
    @Query("SELECT * FROM invoice_lines")
    fun getLinesAndProduct(): List<LinesAndProducts>

    @Insert
    fun insertInvoiceAndCustomerAndLine(
        invoiceEntity: InvoiceEntity,
        customerEntity: CustomerEntity,
        invoiceLinesEntity: List<InvoiceLinesEntity>
    )

    @Transaction
    @Query ("Select * from invoice")
    fun getInvoiceAndCustomer(): List <InvoiceAndCustomer>


    /**
     * Posible punto a mirar
     */
    @Transaction
    @Query("SELECT * FROM invoice_lines WHERE id = :lineId")
    fun findById(lineId: Int): InvoiceLinesEntity

}