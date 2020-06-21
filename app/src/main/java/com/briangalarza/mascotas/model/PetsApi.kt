package com.briangalarza.mascotas.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PetsApi {

    //Endpoint
    @GET("pet/findByStatus?status=available")
    fun getPets():Single<List<Pet>>

    //Endpoint de mascotas
    @GET("pet/{petId}")
    fun getPetById(@Path("petId") id: Long?):Single<Pet>


}