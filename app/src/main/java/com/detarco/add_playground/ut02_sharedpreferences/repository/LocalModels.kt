package com.detarco.add_playground.ut02_sharedpreferences.repository

interface Model {
    fun getId(): Int
}

/**
 * Se crea un objeto con distintas características
 */
data class UserModel(val userId: Int, val name: String, val surname: String) : Model {
    override fun getId(): Int = userId

}