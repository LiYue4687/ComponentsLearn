package com.example.componentslearn.activity

import android.app.Activity
import android.util.Log

object ActivityCollector {
    private val activityList = ArrayList<Activity>()

    fun addActivity(activity: Activity){
        activityList.add(activity)
    }

    fun delActivity(activity: Activity){
        activityList.remove(activity)
    }

    fun clearAll(){
        Log.i("myTest", "Clear All")
        for(activity in activityList){
            if(!activity.isFinishing){
                activity.finish()
            }
        }
        activityList.clear()
    }
}