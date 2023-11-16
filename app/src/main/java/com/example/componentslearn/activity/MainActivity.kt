package com.example.componentslearn.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.componentslearn.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1:Button = findViewById(R.id.button_mainToMain)
        button1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val button2:Button = findViewById(R.id.button_mainToSingleTask)
        button2.setOnClickListener {
            val intent = Intent(this, SingleTask::class.java)
            startActivity(intent)
        }

        val button3:Button = findViewById(R.id.button_mainToSingleInstance)
        button3.setOnClickListener {
            val intent = Intent(this, SingleInstance::class.java)
            startActivity(intent)
        }

        val button4:Button = findViewById(R.id.button_clearAll)
        button4.setOnClickListener {
            ActivityCollector.clearAll()
        }
    }
}