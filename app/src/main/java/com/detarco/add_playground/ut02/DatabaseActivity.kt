package com.detarco.add_playground.ut02

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.detarco.add_playground.R
import com.detarco.add_playground.ut03.ex01.data.AppDataBase
import com.detarco.add_playground.ut03.ex01.data.UserEntity

class DatabaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbactivity)
        initDB()
    }

    private fun initDB() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "demo-db"
        ).build()

        Thread(Runnable {
            var user = db.userDao().findById(1)
            if (user == null) {
                db.userDao().insert(UserEntity(1, "name1", "username1", "email1"))
                user = db.userDao().findById(1)
            }
            Log.d(TAG, "$user")
        }).start()

    }
    //val thread = SimpleThread()
    //thread.start()

}