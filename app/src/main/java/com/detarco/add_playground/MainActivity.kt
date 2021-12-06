package com.detarco.add_playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.detarco.add_playground.ut01.ex01.DataStorageType
import java.io.File

class MainActivity : AppCompatActivity() {

    val colors : MutableList<String> = mutableListOf() //Se inicia vacío

    private lateinit var inputNameFile: AppCompatEditText
    private lateinit var inputContentFile: AppCompatEditText
    private lateinit var buttonSave: AppCompatButton
    private lateinit var buttonShowContent: AppCompatButton
    private lateinit var viewerFiles: TextView
    private lateinit var textFileName: TextView
    private lateinit var textFileContent: TextView

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

        setupView()
    }

    private fun setupView() {
        inputNameFile = findViewById(R.id.input_name_file)
        inputContentFile = findViewById(R.id.input_content_file)
        viewerFiles = findViewById(R.id.viewer_files)
        //textFileName = findViewById(R.id.text_file_name)
        textFileContent = findViewById(R.id.text_file_content)
        buttonSave = findViewById(R.id.action_save)
        buttonSave.setOnClickListener {
            saveFile()
        }
        buttonShowContent = findViewById(R.id.action_show_content)
        buttonShowContent.setOnClickListener {
            showFileContent()
        }

        //Coger texto de un AppCompatEditText
        inputNameFile.text.toString()
        //textFileContent.text = textFileContent.text.toString() + "Hola"
    }

    private fun saveFile(){

        val newFolder = File(this.filesDir, "/activityDocs")

        val newFolderExists = newFolder.exists()

        when {
            newFolderExists -> {
            }
            else -> {
                newFolder.mkdir()
            }
        }

        val newNameFile = inputNameFile.text //.append(".txt") Para que no necesite especificar el usuario
        val newFileText = inputContentFile.text

        val saveFile = File(this.filesDir.canonicalFile, "$newNameFile")// + newNameFile.toString())
        saveFile.writeText(newFileText.toString())
    }

    private fun showFileContent(){
        //file.list().toString()
    }

    private fun initColors(){
        colors.add("Blue")
        colors.add("Red")
        colors.add("Orange")
        colors.add("Green")
    }
}