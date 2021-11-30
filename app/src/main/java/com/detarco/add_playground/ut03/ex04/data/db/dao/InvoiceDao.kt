package com.detarco.add_playground.ut03.ex04.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.detarco.add_playground.ut03.ex04.data.db.entity.InvoiceAndCustomerAndProduct
import com.detarco.add_playground.ut03.ex04.data.db.entity.InvoiceLinesEntity

@Dao
interface InvoiceDao {

    @Transaction
    @Query ("Select * from invoice")
    fun fetchAll(): List <InvoiceAndCustomerAndProduct>

    /**
     * Posible punto a mirar
     */
    @Transaction
    @Query("SELECT * FROM invoice_lines WHERE id = :productCost")
    fun findById(productCost: Int): InvoiceLinesEntity

}