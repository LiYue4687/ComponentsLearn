package com.example.componentslearn.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.componentslearn.R

class SingleTask : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_task)

        val button1: Button = findViewById(R.id.button_singleTaskToMain)
        button1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val button2: Button = findViewById(R.id.button_singleTaskToSingleTask)
        button2.setOnClickListener {
            val intent = Intent(this, SingleTask::class.java)
            startActivity(intent)
        }

        val button3: Button = findViewById(R.id.button_singleTaskToSingleInstance)
        button3.setOnClickListener {
            val intent = Intent(this, SingleInstance::class.java)
            startActivity(intent)
        }
    }
}