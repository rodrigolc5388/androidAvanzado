package com.rodrigolc.madridshops

import android.app.Application
import android.support.multidex.MultiDexApplication
import android.util.Log


class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App", "onCreate")
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

}
