package com.example.androidjetpacklibraries.view


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.androidjetpacklibraries.R
import com.example.androidjetpacklibraries.model.Dog
import com.example.androidjetpacklibraries.modelview.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    var list = arrayListOf<Dog>()
    var adapterList: DogAdapter? = null;
    lateinit var viewModel:ListViewModel;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterList = DogAdapter(list);

        swiperLayout.setOnRefreshListener {
            dogList.visibility = View.GONE
            tvError.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            viewModel.refresh()
            swiperLayout.isRefreshing = false
        }

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()



        dogList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterList
        }

        viewModel.dogList.observe(this, Observer { newList->
            list?.let {
                dogList.visibility = View.VISIBLE
                adapterList!!.updateList(newList as ArrayList<Dog>)
            }
        })

        viewModel.isError.observe(this, Observer {
            it?.let {
                if(it)
                {
                    dogList.visibility = View.GONE
                    tvError.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
                else
                {
                    dogList.visibility = View.VISIBLE
                    tvError.visibility = View.GONE
                }
            }
        })

        viewModel.isLoading.observe(this, Observer {
            it?.let {
                if(it)
                {
                    dogList.visibility = View.GONE
                    tvError.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }
                else
                {
                    dogList.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_item,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.setting_click->{
                view?.let { Navigation.findNavController(it).navigate(ListFragmentDirections.settingFragment()) }
            }
        }


        return super.onOptionsItemSelected(item)

    }

}
