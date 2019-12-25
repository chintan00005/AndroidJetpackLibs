package com.example.androidjetpacklibraries.modelview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidjetpacklibraries.model.Dog
import com.example.androidjetpacklibraries.model.MainApiService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    val dogList = MutableLiveData<List<Dog>>()
    val isError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    val apiService = MainApiService()
    val disposable = CompositeDisposable()


    fun refresh() {
        fetchFromServer()
    }

    fun fetchFromServer() {
        isLoading.value = true

        disposable.add(
            apiService.getDogList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Dog>>() {
                    override fun onSuccess(t: List<Dog>) {
                        isLoading.value = false
                        isError.value = false
                        dogList.value = t
                    }

                    override fun onError(e: Throwable) {
                        isLoading.value = false
                        isError.value = true
                        dogList.value = null
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}