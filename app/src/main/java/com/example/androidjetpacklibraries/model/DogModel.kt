package com.example.androidjetpacklibraries.model

import com.google.gson.annotations.SerializedName

data class Dog(
    @SerializedName("id")
    val dogId:String?,
    @SerializedName("name")
    val dogBreed:String?,
    @SerializedName("life_span")
    val dogLifeSpan:String?,
    @SerializedName("breed_group")
    val dogBreedGroup:String?,
    @SerializedName("bred_for")
    val dogBreedFor:String?,
    @SerializedName("temperament")
    val dogTemperament:String?,
    @SerializedName("url")
    val dogImage:String?
)