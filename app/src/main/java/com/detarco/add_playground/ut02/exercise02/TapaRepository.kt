package com.detarco.add_playground.ut02.exercise02

/**
 * Clase que me permite guardar y recuperar modelos de datos de tipo Tapa
 *
 * Pista: La clase debe ser inicializada con una fuente de datos y un modelo en concreto
 *
 * Pi
 *
 */

class TapaRepository (
    private val localStorage: LocalStorage<TapaLocalModel>
    ){

    /**
     * Guardo un modelo de datos de tipo tapa
     */

    fun save(tapa: TapaLocalModel){
        localStorage.save(tapa)
    }

    /**
     * Recupero un modelo de datos de tipo Tapa
     */

    fun fetch(id: Int):TapaLocalModel? = localStorage.fetch(id.toString())
}
