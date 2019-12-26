package com.example.androidjetpacklibraries.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Dog::class),version = 1)
abstract class MainDatabase : RoomDatabase(){
    abstract fun dogsDao() : DogsDao
    companion object{
        @Volatile private var instance: MainDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?:createDatabase(context).also{
                instance = it
            }
        }

       private fun createDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,MainDatabase::class.java,"dogdatabase").build()

    }
}