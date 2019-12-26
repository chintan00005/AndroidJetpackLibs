package com.example.androidjetpacklibraries.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "dogs")
data class Dog(
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val dogId:String?,

    @ColumnInfo(name = "breed")
    @SerializedName("name")
    val dogBreed:String?,

    @ColumnInfo(name = "lifespan")
    @SerializedName("life_span")
    val dogLifeSpan:String?,

    @ColumnInfo(name = "breedgroup")
    @SerializedName("breed_group")
    val dogBreedGroup:String?,

    @ColumnInfo(name = "breedfor")
    @SerializedName("bred_for")
    val dogBreedFor:String?,

    @ColumnInfo(name = "temperament")
    @SerializedName("temperament")
    val dogTemperament:String?,

    @ColumnInfo(name="image")
    @SerializedName("url")
    val dogImage:String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Long=0
}