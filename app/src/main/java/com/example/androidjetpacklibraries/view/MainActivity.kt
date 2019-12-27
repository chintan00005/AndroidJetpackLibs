package com.example.androidjetpacklibraries.view

import android.app.AlertDialog
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.androidjetpacklibraries.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private lateinit var navigationController:NavController

    val SMS_PER=100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationController = Navigation.findNavController(this,R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this,navigationController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navigationController,null)
    }

    fun checkSmsPermission() {


       if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED)
       {
           if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.SEND_SMS))
           {
                AlertDialog.Builder(this).setTitle("Really??").setMessage("U should!!")
                    .setPositiveButton("Yes"){
                            _, _ ->  requestPermssion()
                    }
                    .setNegativeButton("Nope"){
                            _, _ ->  informFragement(false)
                    }.show()
           }
           else
           {
               requestPermssion()
           }
       }
        else
       {
           informFragement(true)
       }
    }

    private fun informFragement(b: Boolean) {
        val activeFrag = fragment.childFragmentManager.primaryNavigationFragment
        if(activeFrag is DetailFragment)
        {
            (activeFrag as DetailFragment).isPermissionReceived(b)
        }

    }

    private fun requestPermssion() {
        Log.e("request permssion","requ")
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS),SMS_PER)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode)
        {
            SMS_PER->{
                if(grantResults[0]!=PackageManager.PERMISSION_GRANTED)
                {
                    informFragement(false)
                }
                else
                {
                    informFragement(true)
                }
            }
        }
    }
}
