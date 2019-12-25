package com.example.androidjetpacklibraries.modelview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidjetpacklibraries.model.Dog

class DetailsViewModel : ViewModel() {


    val detailData = MutableLiveData<Dog>();

    fun updateDogData( dog : Dog)
    {
        detailData.value = dog
    }
}