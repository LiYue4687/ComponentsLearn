package com.example.componentslearn.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.componentslearn.R

class SingleInstance : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_instance)

        val button1: Button = findViewById(R.id.button_singleInstanceToMain)
        button1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val button2: Button = findViewById(R.id.button_singleInstanceToSingleTask)
        button2.setOnClickListener {
            val intent = Intent(this, SingleTask::class.java)
            startActivity(intent)
        }

        val button3: Button = findViewById(R.id.button_singleInstanceToSingleInstance)
        button3.setOnClickListener {
            val intent = Intent(this, SingleInstance::class.java)
            startActivity(intent)
        }
    }
}