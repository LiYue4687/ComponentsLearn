package com.example.componentslearn.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

open class BaseActivity : ComponentActivity() {
    override fun onResume() {
        super.onResume()
        ActivityCollector.addActivity(this)
        Log.i("myTest", "taskId: $taskId, name: ${this.javaClass.simpleName}")
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.delActivity(this)
    }
}