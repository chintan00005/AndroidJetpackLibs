package com.example.androidjetpacklibraries.modelview

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidjetpacklibraries.model.Dog
import com.example.androidjetpacklibraries.model.MainDatabase
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : BaseViewModel(application) {

    val detailData = MutableLiveData<Dog>();

    fun receiveData( id:String)
    {
        launch {
            val dao = MainDatabase(getApplication()).dogsDao()
            val dog = dao.selectDogbyId(id.toInt())
            detailData.value = dog
        }
    }
}