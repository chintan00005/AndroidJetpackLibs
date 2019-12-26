package com.example.androidjetpacklibraries.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DogsDao{
    @Insert
    suspend fun insertData(vararg dog:Dog):List<Long>

    @Query("select * from dogs")
    suspend fun selectAllData():List<Dog>

    @Query("select * from dogs where uuid=:dogId")
    suspend fun selectDogbyId(dogId:Int):Dog

    @Query("delete from dogs")
    suspend fun deleteTable()
}