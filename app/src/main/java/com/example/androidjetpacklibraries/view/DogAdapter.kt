package com.example.androidjetpacklibraries.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.androidjetpacklibraries.R
import com.example.androidjetpacklibraries.model.Dog
import com.example.androidjetpacklibraries.util.getPlaceHolder
import com.example.androidjetpacklibraries.util.loadImage
import kotlinx.android.synthetic.main.dog_item.view.*

class DogAdapter(val dogList : ArrayList<Dog>) : RecyclerView.Adapter<DogAdapter.DogViewHolder>(){

    fun updateList(listUpdated : ArrayList<Dog>)
    {
        dogList.clear()
        dogList.addAll(listUpdated)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
      return dogList.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.tvDogName.text = dogList[position].dogBreed
        holder.view.tvDogDetails.text = dogList[position].dogBreedGroup
        holder.view.rlView.setOnClickListener {
            Navigation.findNavController(it).navigate(ListFragmentDirections.actionDetailFragment())
        }
        dogList[position].dogImage?.let { holder.view.ivDogImage.loadImage(it,getPlaceHolder( holder.view.ivDogImage.context)) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dog_item,parent,false)
        return DogViewHolder(view)
    }


    class DogViewHolder(val view:View) : RecyclerView.ViewHolder(view)
}