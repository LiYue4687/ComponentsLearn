package com.example.componentslearn.service

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.net.ConnectivityManager
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import com.example.componentslearn.R

class MainActivity : ComponentActivity() {

    lateinit var countBinder:CountService.CountBinder

    private lateinit var timeChangeReceiver: TimeChangeReceiver

    inner class TimeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            callService()
        }
    }

    private val connection = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            countBinder = service as CountService.CountBinder
        }

        override fun onServiceDisconnected(name: ComponentName?) { }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val buttonCount: Button = findViewById(R.id.button_count)

        // 启动Service
        // 第一种，startService
        buttonCount.setOnClickListener {
            callService()
        }
        // 第二种，bind
//        val intent = Intent(this, CountService::class.java)
//        bindService(intent, connection, Context.BIND_AUTO_CREATE )
//        buttonCount.setOnClickListener {
//            countBinder.addCount()
//        }

        // 注册BroadcastReceiver
        val intentFilter = IntentFilter()
        // 这个不生效
        // intentFilter.addAction("android.itent.action.TIME_TICK")
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter, RECEIVER_EXPORTED)

    }

    override fun onDestroy() {
        super.onDestroy()
        val intent = Intent(this, CountService::class.java)
        stopService(intent)
        unregisterReceiver(timeChangeReceiver)
    }

    fun callService(){
        val intent = Intent(this, CountService::class.java)
        startService(intent)
        // 静态注册不能接受部分系统广播
        val intentTimeChange = Intent("myTimeChange")
        // 设置package，显示广播
        intentTimeChange.setPackage(packageName)
        sendBroadcast(intentTimeChange)
    }
}