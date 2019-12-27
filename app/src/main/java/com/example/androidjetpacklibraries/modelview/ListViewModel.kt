package com.example.androidjetpacklibraries.modelview

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidjetpacklibraries.model.Dog
import com.example.androidjetpacklibraries.model.MainApiService
import com.example.androidjetpacklibraries.model.MainDatabase
import com.example.androidjetpacklibraries.util.NotificationHelper
import com.example.androidjetpacklibraries.util.SharedPrefHelper
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class ListViewModel(application: Application) : BaseViewModel(application) {

    val dogList = MutableLiveData<List<Dog>>()
    val isError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    val apiService = MainApiService()
    val disposable = CompositeDisposable()

    val pref = SharedPrefHelper()

   var time :Int=0;


    fun refresh() {
        getDuration()
        if((System.currentTimeMillis() - pref.getTime())>time)
        {
        fetchFromServer()
        }
        else
        {
            fetchFromDb()
        }
    }

    fun getDuration(){
        try {
            val duration =  pref.getCacheDuration()
            time = duration.toInt() * 60 *1000
        }catch (e:NumberFormatException)
        {
            e.printStackTrace()
            time = 10 * 60 * 1000
        }

    }

    fun fetchFromDb()
    {
        isLoading.value = true
        launch {
            val dao = MainDatabase(getApplication()).dogsDao()
            setValuesAfterReciving(   dao.selectAllData())
        }
    }

    fun fetchFromServer() {
        isLoading.value = true

        disposable.add(
            apiService.getDogList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Dog>>() {
                    override fun onSuccess(t: List<Dog>) {
                        insertIntoDb(t)
                        NotificationHelper(getApplication()).createNotification()
                    }

                    override fun onError(e: Throwable) {
                        isLoading.value = false
                        isError.value = true
                        dogList.value = null
                    }
                })
        )
    }

    fun insertIntoDb(list:List<Dog>){
        launch {
            val dao = MainDatabase(getApplication()).dogsDao()
            dao.deleteTable()
            val result = dao.insertData(*list.toTypedArray())
            var i =0
            while(i<list.size)
            {
                list[i].uuid = result[i]
                i++
            }
            pref.saveTime(System.currentTimeMillis())
            setValuesAfterReciving(list)
        }

    }



    fun setValuesAfterReciving(list:List<Dog>){
        isLoading.value = false
        isError.value = false
        dogList.value = list
    }
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}