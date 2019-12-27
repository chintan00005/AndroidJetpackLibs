package com.example.androidjetpacklibraries.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.preference.PreferenceFragmentCompat
import com.example.androidjetpacklibraries.R

/**
 * A simple [Fragment] subclass.
 */
class SettingFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference,rootKey)
    }


}
