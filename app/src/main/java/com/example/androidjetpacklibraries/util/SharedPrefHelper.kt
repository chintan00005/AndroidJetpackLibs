package com.example.androidjetpacklibraries.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SharedPrefHelper {
    companion object{
        val PREF_TIME="prefTime"
        private var pref:SharedPreferences?=null;
        @Volatile private var instance:SharedPrefHelper?=null
        private val LOCK = Any()
        operator fun invoke(context: Context):SharedPrefHelper = instance?: synchronized(LOCK){
            instance?:buildObject(context).also{
                instance=it
            }
        }
        fun buildObject(context: Context):SharedPrefHelper{
            pref = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPrefHelper()
        }
    }

    fun saveTime(time:Long){
        pref?.edit(commit=true){
            putLong(PREF_TIME,time)
        }
    }

    fun getTime(): Long {
        return pref?.getLong(PREF_TIME,0)?:0L;
    }
    fun getCacheDuration(): String {
        return pref?.getString("cache_time","15")?:"20";
    }

}