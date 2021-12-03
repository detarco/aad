package com.detarco.add_playground.ut03.ex04.data.local.db

import android.content.Context
import com.detarco.add_playground.ut03.ex04.app.Ut03Ex04DataBase
import com.detarco.add_playground.ut03.ex04.data.local.db.entity.ProductEntity
import com.detarco.add_playground.ut03.ex04.domain.ProductModel
import com.detarco.add_playground.ut03.ex04.domain.repository.ProductRepository

class ProductDBLocalSource(private val appContext: Context) : ProductRepository {

    private val db: Ut03Ex04DataBase by lazy {

        Ut03Ex04DataBase.getInstance(appContext)

    }

    init {
        Thread {
            db.clearAllTables()
        }.start()
        Thread.sleep(1000)
    }

    override fun save(product: ProductModel) {
        db.productDao().insert(ProductEntity.toEntity(product))
    }

    override fun update(productId: Int): ProductModel {
        val updateProd = db.productDao().findById(productId).toModel()

        return  updateProd.also {
            db.productDao().insert(ProductEntity.toEntity(it))
        }
    }

    override fun delete(productId: Int): ProductModel {
        val delProd = db.productDao().findById(productId).toModel()
        return delProd.apply {
            db.productDao().delete()
        }
    }

    override fun findAll(): List<ProductModel> {
        val product = db.productDao().fetchAll()
        /**
         * Devuélve de modelos, no entidades(?)
         */
        return product.map { element -> element.toModel() }
    }

    override fun findById(productId: Int): ProductModel {
        return db.productDao().findById(productId).toModel()
    }

}