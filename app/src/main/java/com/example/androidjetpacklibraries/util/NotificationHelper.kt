package com.example.androidjetpacklibraries.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.androidjetpacklibraries.R
import com.example.androidjetpacklibraries.view.MainActivity

class NotificationHelper(val context: Context) {
    private val CHANNEL_ID="dogChannel"
    private val NOTIFICATION_ID=123

    fun createChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            val name = "DosUpdate"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val desc = "AnyDescription"
            val channel = NotificationChannel(CHANNEL_ID,name,importance)
            channel.description = desc
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification(){
        createChannel()
        val intent = Intent(context,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = PendingIntent.getActivity(context,0,intent,0)

        val icon = BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher)

        val notification = NotificationCompat.Builder(context,CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(icon)
            .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
            .setContentIntent(pendingIntent)
            .setContentTitle("Dogs")
            .setContentText("Got from server")
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(icon)
                    .bigLargeIcon(null)
            )
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID,notification)
    }
}