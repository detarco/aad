package com.detarco.add_playground.ut02.exercise02

import androidx.appcompat.app.AppCompatActivity
import com.detarco.add_playground.R
import com.detarco.add_playground.commons.Serializer

class DataSourceFactory<T : LocalModel> (
    private val activity: AppCompatActivity,
    private val serializer: Serializer<T>
    ){

    fun create (idActionClicked: Int): DataSource<T>{
        return  when (idActionClicked){
            R.id.action_repository_file -> FileDataSource(activity, serializer)
            R.id.SharedPref_Button_Action -> SharPrefDataSource(activity, serializer)
            else -> MemDataSource()
        }
    }

}