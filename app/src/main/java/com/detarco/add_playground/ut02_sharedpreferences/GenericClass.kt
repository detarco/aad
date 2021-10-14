package com.detarco.add_playground.ut02_sharedpreferences

import com.detarco.add_playground.ut02_sharedpreferences.repository.UserModel

class GenericClass<T> {

    fun getName(model: T): String{
        return ""
    }

}

class MainClass{

    fun initMain(){
        //val genericClass1 = List<AnimalClass>()
        val genericClass2 = GenericClass<UserModel>()
    }
}

class AnimalClass{

}
