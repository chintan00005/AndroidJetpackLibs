package com.example.androidjetpacklibraries.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.example.androidjetpacklibraries.R
import com.example.androidjetpacklibraries.model.Dog
import com.example.androidjetpacklibraries.modelview.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    lateinit var viewModel:DetailsViewModel;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        viewModel.updateDogData(Dog("dogId1","dogBreed","dogLifeSpan","dogBreedGroup","dogBreedFor","dogTemperament","dogImage"))

        viewModel.detailData.observe(this, Observer {
            it?.let {
                tvTitle.text = it.dogBreed
                tvDesc.text = it.dogBreedGroup
                tvTemperament.text = it.dogTemperament
                lifeSpan.text = it.dogLifeSpan

            }
        })

    }


}
