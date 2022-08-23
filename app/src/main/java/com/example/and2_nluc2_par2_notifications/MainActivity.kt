package com.example.and2_nluc2_par2_notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.and2_nluc2_par2_notifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotifi.setOnClickListener {
            createNotificationChannel()
        }
    }

    private fun createNotificationChannel(){
        val CHANNEL_ID = "ServiceChannelExample"
        var manager : NotificationManager?= null

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val serviceChannel = NotificationChannel(CHANNEL_ID,
                                               "Example Service Channel",
                                                     NotificationManager.IMPORTANCE_DEFAULT)
            manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }else{
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivities(this, 0, arrayOf(notificationIntent),0)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Service Example")
            .setContentText("Bilal Osama Alkhatib")
            .setSmallIcon(R.drawable.ic_notifications_24)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_larg_notifications))
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.ic_larg_notifications))

            )//حتى يظهر السهم الخاص بالسحب
                //لاضهار زر
           // .addAction(0, "Reply",100,  Intent(this,MainActivity::class.java ),PendingIntent.FLAG_UPDATE_CURRENT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        manager!!.notify(1,notification)


    }
}