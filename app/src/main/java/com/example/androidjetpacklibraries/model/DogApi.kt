package com.example.androidjetpacklibraries.model

import io.reactivex.Single
import retrofit2.http.GET

interface DogApi {
    @GET("DevTides/DogsApi/master/dogs.json")
    fun getDogList():Single<List<Dog>>
}