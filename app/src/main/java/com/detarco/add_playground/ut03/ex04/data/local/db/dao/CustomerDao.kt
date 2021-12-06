package com.detarco.add_playground.ut03.ex04.data.local.db.dao

import androidx.room.*
import com.detarco.add_playground.ut03.ex04.data.local.db.entity.CustomerEntity

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