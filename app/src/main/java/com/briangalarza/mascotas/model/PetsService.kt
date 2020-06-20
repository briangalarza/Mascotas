package com.briangalarza.mascotas.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class PetsService {

    private val BASE_URL = "https://petstore.swagger.io/v2/"

    lateinit var  api:PetsApi

    init {
        api= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(PetsApi::class.java)
    }

    //Obtenemos las mascotas
    fun getPets(): Single<List<Pet>> {
        return api.getPets()
    }
}