package com.detarco.add_playground.ut03.ex03.data.local.dao

import androidx.room.*
import com.detarco.add_playground.ut03.ex03.data.local.entity.ClothesEntity
import com.detarco.add_playground.ut03.ex03.data.local.entity.CustomerAndClothes
import com.detarco.add_playground.ut03.ex03.data.local.entity.CustomerEntity

@Dao
interface CustomerDao {

    @Update
    fun update(vararg customerEntity: CustomerEntity)

    @Delete
    fun delete(vararg customerEntity: CustomerEntity)

    //@Transaction
    @Query("SELECT * FROM customer")
    fun findAll(): List<CustomerEntity>

    @Insert//(onConflict = IGNORE)
    fun insert(customerEntity: CustomerEntity): Long

    @Insert
    fun insertCustomerAndClothes(
        customerEntity: CustomerEntity,
        clothesEntities: List<ClothesEntity>
    )

    @Transaction
    @Query("SELECT * FROM customer")
    fun getCustomerAndClothes(): List<CustomerAndClothes>

}
