package com.example.componentslearn.service

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
import android.widget.Toast
import androidx.core.app.NotificationCompat


class CountService : Service() {
    companion object {
        var count:Int = 0
    }

    private val cBinder = CountBinder()

    class CountBinder: Binder() {
        fun addCount(){
            count++
            Log.i("myTest", "Current Count is $count")
        }

        fun test(){
            Log.i("myTest", "Count test")
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("myTest", "Count create 1")
        // 创建成前台service
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        Log.i("myTest", "Count create 2")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("CountService", "前台通知",
                NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        Log.i("myTest", "Count create 3")
        val intent = Intent(this, MainActivity::class.java)
        Log.i("myTest", "Count create 4")
        val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        Log.i("myTest", "Count create 5")
        val notification = NotificationCompat.Builder(this, "CountService")
            .setContentTitle("count service")
            .setContentText("is counting")
            .setContentIntent(pi)
            .build()
        Log.i("myTest", "Count create 6")
        startForeground(1, notification)

        Log.i("myTest", "Count created")
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        count++
        Log.i("myTest", "Current Count is $count")
        return super.onStartCommand(intent, flags, startId)

        }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("myTest", "Count destroyed")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return cBinder
    }

}