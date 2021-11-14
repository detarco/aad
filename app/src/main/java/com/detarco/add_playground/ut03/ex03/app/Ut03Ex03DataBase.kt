package com.detarco.add_playground.ut03.ex03.app

import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase
import com.detarco.add_playground.ut03.ex03.data.local.dao.ClothesDao
import com.detarco.add_playground.ut03.ex03.data.local.dao.CustomerDao
import com.detarco.add_playground.ut03.ex03.data.local.entity.CustomerEntity
import com.detarco.add_playground.ut03.ex03.data.local.entity.ClothesEntity

import kotlin.jvm.Volatile;

@Database(
    entities = [CustomerEntity::class, ClothesEntity::class],
    version = 1,
    exportSchema = false
)

abstract class Ut03Ex03DataBase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun clothesDao(): ClothesDao

    companion object {
        @Volatile
        private var instance: Ut03Ex03DataBase? = null

        fun getInstance(applicationContext: Context): Ut03Ex03DataBase {
            //Puede que algo se haya cambiado
            if (instance == null) {
                instance = buildDataBase(applicationContext).also { instance = it }
            }
            return instance as Ut03Ex03DataBase
        }

        private fun buildDataBase(applicationContext: Context): Ut03Ex03DataBase {
            return Room.databaseBuilder(
                applicationContext,
                Ut03Ex03DataBase::class.java,
                "db-ut03-ex03"
            ).build()
        }
    }
}
