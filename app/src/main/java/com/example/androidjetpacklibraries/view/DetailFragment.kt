package com.example.androidjetpacklibraries.view


import android.os.Bundle
import android.util.Log
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
import com.example.androidjetpacklibraries.util.getPlaceHolder
import com.example.androidjetpacklibraries.util.loadImage
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
        var dogId=0
        arguments?.let {
            dogId = DetailFragmentArgs.fromBundle(it).dogid
            Log.e("dogId",""+dogId)
            viewModel.receiveData(dogId.toString())
        }



        viewModel.detailData.observe(this, Observer {
            it?.let {
                tvTitle.text = it.dogBreed
                tvDesc.text = it.dogBreedGroup
                tvTemperament.text = it.dogTemperament
                lifeSpan.text = it.dogLifeSpan
                it.dogImage?.let { it1 ->
                    context?.let { it2 -> getPlaceHolder(it2) }?.let { it3 ->
                        ivDogImage.loadImage(it1,
                            it3
                        )
                    }
                }

            }
        })

    }


}
