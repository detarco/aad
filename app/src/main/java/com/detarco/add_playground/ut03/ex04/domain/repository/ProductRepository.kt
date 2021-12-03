package com.detarco.add_playground.ut03.ex04.domain.repository

import com.detarco.add_playground.ut03.ex04.domain.ProductModel

interface ProductRepository {

    fun save(product: ProductModel)

    //update
    fun update(productId: Int): ProductModel?

    //delete
    fun delete(productId: Int): ProductModel?

    fun findAll(): List<ProductModel>

    fun findById(productId: Int): ProductModel?

}