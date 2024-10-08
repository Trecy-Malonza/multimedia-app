package com.trecy.multimedia

import android.app.Application
import android.content.Context
import java.util.Objects

class MyApp: Application() {
     companion object{
         lateinit var appContext: Context
     }

    override fun  onCreate(){
        super.onCreate()
        appContext= applicationContext
    }
}