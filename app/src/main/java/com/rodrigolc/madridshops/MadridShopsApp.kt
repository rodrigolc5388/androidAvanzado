package com.rodrigolc.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log


class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        Log.d("MadridShopsApp", "onCreate")
    }


    override fun onLowMemory() {
        super.onLowMemory()
    }

}