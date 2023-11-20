package com.example.componentslearn.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.componentslearn.R


class CountService : Service() {
    companion object {
        var count:Int = 0
    }

    private val cBinder = CountBinder()

    private lateinit var manager:NotificationManager

    class CountBinder: Binder() {
        fun addCount(){
            count++
            Log.i("myTest", "Current Count is $count")
        }

        fun test(){
            Log.i("myTest", "Count test")
        }

//        fun updateNotification(){
//
//        }
    }

    override fun onCreate() {
        super.onCreate()
        // 创建成前台service
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("CountService", "前台通知",
                NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        startForeground(1, getNotification())

        Log.i("myTest", "Count created")
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        count++
        Log.i("myTest", "Current Count is $count")
        updateNotification()
        return super.onStartCommand(intent, flags, startId)
        }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("myTest", "Count destroyed")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return cBinder
    }

    private fun getNotification(): Notification {
        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        return NotificationCompat.Builder(this, "CountService")
            .setContentTitle("count service")
            .setContentText("Current Count is $count")
            .setContentIntent(pi)
            .setSmallIcon(R.drawable.img)
            .build()
    }

    private fun updateNotification(){
        manager.notify(1, getNotification())
    }

}