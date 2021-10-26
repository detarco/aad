package com.detarco.add_playground.ut02

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.detarco.add_playground.R

class SharedPreferencesActivity:AppCompatActivity(){
    private val TAG = SharedPreferencesActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)
        initExampleAsync()
        initExampleEncryptedAsync()
        initExampleSync()
        initExampleEncryptedSync()
    }

    private fun initExampleAsync() {
        val localDataSource = LocalDataSource(this)
        localDataSource.saveAsync("AsyncExample", "Primer Ejemplo de Shared Prefs Asíncrono sin Encriptar")
        val data = localDataSource.read("AsyncExample")
        //Log.d(TAG, data!!)
        data?.let{ aSyncEx ->
            Log.d(TAG, aSyncEx)
        }
    }

    private fun initExampleEncryptedAsync() {
        val localDataSource = LocalDataSource(this)
        localDataSource.saveAsyncEncrypt("EncryptedAsyncExample", "Ejemplo de Shared Prefs Asíncrono Encriptado")
        val data = localDataSource.readEncrypt("EncryptedAsyncExample")
        data?.let{  encryptedASyncEx ->
            Log.d(TAG, encryptedASyncEx)
        }
    }

    private fun initExampleSync(){
        val localDataSource = LocalDataSource(this)
        localDataSource.saveSync("SyncExample", "Ejemplo de Shared Prefs Síncrono")
        val data = localDataSource.read("SyncExample")

        data?.let { syncEx ->
            Log.d(TAG, syncEx)
        }

        /**
         *  data?.run{
         *      Log.d(TAG, this)
         *  }
         */

    }

    private fun initExampleEncryptedSync(){
        val localDataSource = LocalDataSource(this)
        localDataSource.saveSyncEncrypt("EncryptedSyncExample","Ejemplo de Shared Prefs Síncrono Encriptado")
        val data = localDataSource.readEncrypt("EncryptedSyncExample")

        data?.let{  encryptedSyncEx ->
            Log.d(TAG, encryptedSyncEx)

        }
    }

}