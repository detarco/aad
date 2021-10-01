package com.detarco.add_playground.ut_01

import androidx.appcompat.app.AppCompatActivity
import java.io.File

class DataStorageType (private val activity: AppCompatActivity){

    /**
     * Fichero específico
     * Las aplicaciones no pueden acceder a estos ficheros
     * No necesitan de permisos especiales
     * Los ficheros desaparecen si se desinstala la aplicación
     */
    fun privateFile(){
        val privateFile = File(activity.filesDir, "private.txt")
        privateFile.writeText("Fichero privado en el directirio de la app")
    }

    /**
     * Private caché
     */
    fun privateFileCache(){
        val privateFileCache = File(activity.cacheDir, "private_cache.txt")
        privateFileCache.writeText("Fichero caché privado")

    }

    /**
     * Fichero privado en el emulador, se guarda en sdcard/Android/data/files
     */
    fun privateExternalFile(){
        val externalFile = File(activity.getExternalFilesDir("path_directory"), "external.txt")
        externalFile.writeText("Privado en un almacenamiento externo")
    }

    fun privateExternalCacheFile(){
        val externalCacheFile = File(activity.externalCacheDir, "cache_external_dir.txt")
        externalCacheFile.writeText("External Cache Privado")
    }

}