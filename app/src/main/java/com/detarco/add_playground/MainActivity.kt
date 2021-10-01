package com.detarco.add_playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.detarco.add_playground.ut_01.DataStorageType
import com.detarco.add_playground.ut_01.FilePlayGround

class MainActivity : AppCompatActivity() {

    val colors : MutableList<String> = mutableListOf() //Se inicia vacío

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initColors()
        //val filePlayGround = FilePlayGround(this)
        //filePlayGround.saveToFile(colors)

        //val lst = filePlayGround.readFromFile()
        //lst.forEach {
        //    Log.d("@dev", it)
        //}

        //val filePlayGround = FilePlayGround(this)
        //filePlayGround.createFolder()
        //filePlayGround.createFileInFolder()

        val dataStorageType = DataStorageType(this)
        //dataStorageType.privateFile()
        //dataStorageType.privateFileCache()
        //dataStorageType.privateExternalFile()
        //dataStorageType.privateExternalCacheFile()
    }

    private fun initColors(){
        colors.add("Blue")
        colors.add("Red")
        colors.add("Orange")
        colors.add("Green")
    }
}