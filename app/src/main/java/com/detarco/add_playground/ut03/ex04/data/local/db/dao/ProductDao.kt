package com.detarco.add_playground.ut03.ex04.data.local.db.dao

import androidx.room.*
import com.detarco.add_playground.ut03.ex04.data.local.db.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert
    fun insert(productEntity: ProductEntity)//: Long

    @Update
    fun update(vararg productEntity: ProductEntity)

    @Delete
    fun delete(vararg productEntity: ProductEntity)

    @Query("SELECT * FROM products")
    fun fetchAll(): List<ProductEntity>

    @Query("SELECT * FROM products WHERE id = :productId")
    fun findById(productId: Int): ProductEntity

}