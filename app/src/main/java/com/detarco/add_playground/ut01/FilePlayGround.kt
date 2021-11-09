package com.detarco.add_playground.ut01

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.File

//

class FilePlayGround(private val activity:AppCompatActivity) {

    //Función que se ejecuta al instanciar la clase (crear un objeto)

    init {
        //createFile()
        //writeFile()
        //readFile()
        //appendText()
        //appendTextWithNewLine()
        //readLineByLine()
        //deleteFile()
        //createFolder()
        //createFileInFolder()
    }

    /**
     * filesDir te da la ruta "a la android"
     */
    fun createFile(){
        val file = File(activity.filesDir, "add.txt")
        Log.d("@dev", activity.filesDir.absolutePath) //Permite verlo en el logcat
    }

    fun writeFile(){
        val file = File(activity.filesDir, "add.txt")
        file.writeText("Hola Acceso a Datos")
    }

    fun readFile(){
        val file = File(activity.filesDir, "add.txt")
        val line = file.readText()
        Log.d("@dev", line)
    }

    /**
     * Se puede sobreescribir
      */

    fun appendText(){
        val file = File(activity.filesDir, "add.txt")
        file.appendText("Hola")
        file.appendText("Hola2")
        file.appendText("Hola3")
        file.appendText("Hola4")
        val line = file.readText()
        Log.d("@dev", line)
    }

    fun appendTextWithNewLine(){
        val file = File(activity.filesDir, "add.txt")
        file.appendText("\n")
        file.appendText("Adiós1")
        file.appendText("\n")
        file.appendText("Adiós2")
        val line = file.readText()
        Log.d("@dev", line)
    }

    fun readLineByLine(){
        val file = File(activity.filesDir, "add.txt")
        file.writeText("Línea 1")
        file.appendText("\n")
        file.appendText("Línea 2")
        file.appendText("\n")
        file.appendText("Línea 3")

        //Kotlin permite omitir la declaracion (: List <String>)
        val lines : List <String> = file.readLines()
        lines.forEach{
            Log.d("@dev", it)
        }
    }

    fun deleteFile(){
        val file = File(activity.filesDir, "add.txt")
        if (file.exists())
            file.delete()
    }

    //Guarda un array de string
    fun saveToFile(colors : MutableList<String>) {
        val file = File(activity.filesDir, "colors.txt")

        if (file.exists()){
            file.writeText("")
        }

        colors.forEach{
            file.appendText("$it \n")
        }

        //colors.forEach{
        //    color -> color
        //}

    }

    fun readFromFile() : MutableList<String> {

        val colors = mutableListOf<String>()

        val file = File(activity.filesDir, "colors.txt")


        if (file.exists()) {
            return colors
        }

        file.readLines().forEach {
            colors.add(it)
        }

        return colors
    }

        //fun readFromFile() =
        //val file = File(activity.filesDir, "colors.txt").readLines().toMutableList()

        fun createFolder(){
            //Opción 1
            val file = File(activity.filesDir, "/docs")
            file.mkdir()

            //Opción 2, hay otra pero para  no preocuparnos de los cambios de versiones nos quedamos con esa

        }

        fun createFileInFolder(){
        val file = File(activity.filesDir.canonicalFile, "/docs/aad.txt")
            file.writeText("Hola!!")
            //file.createNewFile
    }

    fun listFilesInFolder(){
        val file = File(activity.filesDir, "/documents")
        val files = file.list()
        files.forEach {
            Log.d("@dev", "File: $it")
        }

    }



}