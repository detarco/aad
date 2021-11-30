package com.detarco.add_playground.ut03.ex04.data.db.dao

import androidx.room.*
import com.detarco.add_playground.ut03.ex04.data.db.entity.CustomerEntity
import com.detarco.add_playground.ut03.ex04.data.db.entity.ProductEntity
import com.detarco.add_playground.ut03.ex04.domain.ProductModel

@Dao
interface CustomerDao {

    @Insert
    fun insert(customerEntity: CustomerEntity)//: Long

    @Update
    fun update(vararg customerEntity: CustomerEntity)

    @Delete
    fun delete(vararg customerEntity: CustomerEntity)

    @Query("SELECT * FROM customers")
    fun fetchAll(): List<CustomerEntity>

    @Query("SELECT * FROM customers WHERE id = :customerId")
    fun findById(customerId: Int): CustomerEntity

}