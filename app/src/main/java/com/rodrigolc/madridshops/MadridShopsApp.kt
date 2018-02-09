package com.rodrigolc.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.rodrigolc.madridshops.domain.interactor.ErrorCompletion
import com.rodrigolc.madridshops.domain.interactor.SuccessCompletion
import com.rodrigolc.madridshops.domain.interactor.getAllShops.GetAllShoptivitiesInteractor
import com.rodrigolc.madridshops.domain.interactor.getAllShopshops.GetAllShoptivitiesInteractorImplementation


import com.rodrigolc.madridshops.domain.model.Shoptivities


class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App", "onCreate")


        //val allShopsInteractor = GetAllShoptivitiesInteractorImplementation(this)
        val allShopsInteractor2: GetAllShoptivitiesInteractor = GetAllShoptivitiesInteractorImplementation(this)

        allShopsInteractor2.execute(object: SuccessCompletion<Shoptivities> {
            override fun successCompletion(shoptivities: Shoptivities) {
                Log.d("Shoptivities", "Count: " + shoptivities.count())

                shoptivities.shoptivities.forEach { Log.d("Shoptivity", it.name) }
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