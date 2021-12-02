package com.detarco.add_playground.ut03.ex04.data.local.db

import android.content.Context
import com.detarco.add_playground.ut03.ex04.app.Ut03Ex04DataBase
import com.detarco.add_playground.ut03.ex04.data.local.CustomerLocalSource
import com.detarco.add_playground.ut03.ex04.data.local.ProductLocalSource
import com.detarco.add_playground.ut03.ex04.data.local.db.entity.ProductEntity
import com.detarco.add_playground.ut03.ex04.domain.CustomerModel
import com.detarco.add_playground.ut03.ex04.domain.ProductModel

class ProductDBLocalSource(private val appContext: Context) : ProductLocalSource {

    private val db: Ut03Ex04DataBase by lazy {

        Ut03Ex04DataBase.getInstance(appContext)

    }

    override fun save(product: ProductModel) {
        db.productDao().insert(ProductEntity.toEntity(product))
    }

    override fun update(productId: Int): ProductModel? {
        TODO("Not yet implemented")
    }

    override fun delete(productId: Int): ProductModel? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<ProductModel> {
        TODO("Not yet implemented")
    }

    override fun findById(productId: Int): ProductModel? {
        TODO("Not yet implemented")
    }

}