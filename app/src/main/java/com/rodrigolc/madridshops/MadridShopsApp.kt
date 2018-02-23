package com.rodrigolc.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.rodrigolc.madridshops.domain.interactor.getAllShops.GetAllShoptivitiesInteractor
import com.rodrigolc.madridshops.domain.interactor.getAllShopshops.GetAllShoptivitiesInteractorImplementation


class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        Log.d("App", "onCreate")


        val allShopsInteractor: GetAllShoptivitiesInteractor = GetAllShoptivitiesInteractorImplementation(this)

        /*allShopsInteractor.execute(object: SuccessCompletion<Shoptivities> {
            override fun successCompletion(shoptivities: Shoptivities) {
                //Log.d("Shoptivities", "Count: " + shoptivities.count())

                shoptivities.shoptivities.forEach { Log.d("Shoptivity", it.name) }
            }
        }, object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Log.d("ERROR MESSAGE", errorMessage)
            }
        })*/
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

}