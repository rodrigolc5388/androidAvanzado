package com.rodrigolc.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.rodrigolc.madridshops.domain.interactor.ErrorCompletion
import com.rodrigolc.madridshops.domain.interactor.SuccessCompletion
import com.rodrigolc.madridshops.domain.interactor.getAllShopshops.GetAllShopsInteractorImplementation

import com.rodrigolc.madridshops.domain.model.Shops


class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App", "onCreate")


        val allShopsInteractor = GetAllShopsInteractorImplementation(this)

        allShopsInteractor.execute(object: SuccessCompletion<Shops> {
            override fun successCompletion(shops: Shops) {
                Log.d("Shops", "Count: " + shops.count())

                shops.shops.forEach { Log.d("Shop", it.name) }
            }
        }, object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Log.d("ERROR MESSAGE", errorMessage)
            }
        })

        /*DeleteAllShopsImpl(this).execute(success = {

        }, error = {

        })*/

    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

}