package com.example.componentslearn.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import com.example.componentslearn.R

class MainActivity : ComponentActivity() {

    lateinit var countBinder:CountService.CountBinder

    private val connection = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            countBinder = service as CountService.CountBinder
//            countBinder.addCount()
//            countBinder.test()
        }

        override fun onServiceDisconnected(name: ComponentName?) { }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val buttonCount: Button = findViewById(R.id.button_count)

        // 第一种，startService
        buttonCount.setOnClickListener {
            val intent = Intent(this, CountService::class.java)
            startService(intent)
        }

        // 第二种，bind
//        val intent = Intent(this, CountService::class.java)
//        bindService(intent, connection, Context.BIND_AUTO_CREATE )
//        buttonCount.setOnClickListener {
//            countBinder.addCount()
////            countBinder.test()
//        }

    }
}