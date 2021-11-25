package com.detarco.add_playground.ut02.ex02

interface LocalModel {
    fun getId(): Int
}

data class TapaLocalModel(val tapaId: Int, val name: String, val description: String): LocalModel{

    override fun getId(): Int = tapaId
    //Se pueden combinar varios elementos para complementar un id y hacerlo único

}
