package com.detarco.add_playground.ut03.ex03.app

import com.detarco.add_playground.ut03.ex03.data.remote.AlertApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * EndPoints de los servicios que se van a usar.
 * Es requisito de Retrofit crear esta interfaz.
 */
interface ApiEndPoint {
    @GET("alerts")
    fun getAlerts(): Call<ApiResponse<List<AlertApiModel>>>

    @GET("alerts/{alert_id}")
    fun getAlert(@Path("alert_id") alertId: String): Call<ApiResponse<AlertApiModel>>
}
