package com.detarco.add_playground.ut03.ex02.app;

import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase
import com.detarco.add_playground.ut03.ex02.data.*
import com.detarco.add_playground.ut03.ex02.data.local.dao.*

import kotlin.jvm.Volatile;

@Database(
        entities = [PersonEntity::class, PetEntity::class, CarEntity::class, JobEntity::class, PersonJobEntity::class],
        version = 1,
        exportSchema = false
)

abstract class Ut03Ex02DataBase : RoomDatabase() {

        abstract fun personDao(): PersonDao
        abstract fun petDao(): PetDao
        abstract fun carDao(): CarDao
        abstract fun jobDao(): JobsDao
        abstract fun personJobDao(): PersonJobDao

        companion object {
                @Volatile
                private var instance: Ut03Ex02DataBase? = null

                fun getInstance(applicationContext: Context): Ut03Ex02DataBase {
                        //Puede que algo se haya cambiado
                        if (instance == null) {
                                instance = buildDataBase(applicationContext).also { instance = it }
                        }
                        return instance as Ut03Ex02DataBase
                }

                private fun buildDataBase(applicationContext: Context): Ut03Ex02DataBase {
                        return Room.databaseBuilder(
                                applicationContext,
                                Ut03Ex02DataBase::class.java,
                                "db-ut03-ex02"
                        ).build()
                }
        }
}
