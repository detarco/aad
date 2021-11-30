package com.detarco.add_playground.ut03.ex04.app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.detarco.add_playground.ut03.ex04.data.db.*

@Database(
    entities = [CustomerEntity::class, ProductEntity::class, InvoiceLinesEntity::class, InvoiceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Ut03Ex04DataBase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun productDao(): ProductDao
    abstract fun invoiceLinesDao(): InvoiceLinesDao
    abstract fun invoiceDao(): InvoiceDao

    /**
     * A través del patrón de diseño SINGLETON creamos una instancia de la base de datos
     * y nos aseguramos que no se creen más.
     */

    companion object {
        @Volatile
        private var instance: Ut03Ex04DataBase? = null

        fun getInstance(applicationContext: Context): Ut03Ex04DataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(applicationContext).also { instance = it }
            }
        }

        private fun buildDatabase(applicationContext: Context): Ut03Ex04DataBase {
            return Room.databaseBuilder(
                applicationContext,
                Ut03Ex04DataBase::class.java,
                "db-ut03-ex04-customers"
            ).build()
        }
    }
}