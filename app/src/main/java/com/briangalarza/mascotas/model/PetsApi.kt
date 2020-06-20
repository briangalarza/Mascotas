package com.briangalarza.mascotas.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface PetsApi {

    //Endpoint
    @GET("pet/findByStatus?status=available")
    fun getPets():Single<List<Pet>>

}