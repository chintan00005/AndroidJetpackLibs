package com.example.androidjetpacklibraries.view


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

import com.example.androidjetpacklibraries.R
import com.example.androidjetpacklibraries.databinding.FragmentDetailBinding
import com.example.androidjetpacklibraries.model.Dog
import com.example.androidjetpacklibraries.model.DogPaller
import com.example.androidjetpacklibraries.modelview.DetailsViewModel
import com.example.androidjetpacklibraries.util.getPlaceHolder
import com.example.androidjetpacklibraries.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    lateinit var viewModel:DetailsViewModel;
    lateinit var dataBinding:FragmentDetailBinding
    var sendProcessStarted = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //inflate(R.layout.fragment_detail, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater,R.layout.fragment_detail, container, false)
        setHasOptionsMenu(true)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        var dogId=0
        arguments?.let {
            dogId = DetailFragmentArgs.fromBundle(it).dogid
            //Log.e("dogId",""+dogId)
            viewModel.receiveData(dogId.toString())
        }



        viewModel.detailData.observe(this, Observer {
            it?.let {
                dataBinding.dogDetail = it
                it.dogImage?.let { it1 -> loadBackground(it1) }
            }
        })
    }

    fun loadBackground(uri:String){
      Glide.with(this).asBitmap().load(uri).into(object :CustomTarget<Bitmap>(){
            override fun onLoadCleared(placeholder: Drawable?) {

            }

            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
               Palette.from(resource).generate {pal->
                   val color = pal?.getDominantColor(0)
                   val myPal = color?.let { it1 -> DogPaller(it1) }
                    dataBinding.dogPal = myPal
               }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detail_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.sharenow->{
                sendProcessStarted = true
                (activity as MainActivity).checkSmsPermission()
            }
            R.id.sendnow->{

            }
        }

        return super.onOptionsItemSelected(item)
    }


    fun isPermissionReceived(permi : Boolean)
    {

    }

}
