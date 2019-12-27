package com.example.androidjetpacklibraries.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.androidjetpacklibraries.R
import com.example.androidjetpacklibraries.databinding.DogItemBinding
import com.example.androidjetpacklibraries.model.Dog
import com.example.androidjetpacklibraries.util.getPlaceHolder
import com.example.androidjetpacklibraries.util.loadImage
import kotlinx.android.synthetic.main.dog_item.view.*

class DogAdapter(val dogList : ArrayList<Dog>) : RecyclerView.Adapter<DogAdapter.DogViewHolder>(),
    DogListner {

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
        holder.view.dog = dogList[position]
        holder.view.listner = this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = DataBindingUtil.inflate<DogItemBinding>(LayoutInflater.from(parent.context),R.layout.dog_item,parent,false)
        return  DogViewHolder(view)
    }



    class DogViewHolder(val view:DogItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onViewClick(view :View,dogUUId:Long) {
            val action = ListFragmentDirections.actionDetailFragment()
            action.dogid = (dogUUId).toInt()
            Navigation.findNavController(view).navigate(action)
    }
}