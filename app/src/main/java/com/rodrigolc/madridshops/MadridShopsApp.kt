package com.rodrigolc.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.rodrigolc.madridshops.domain.Interactor.ErrorCompletion
import com.rodrigolc.madridshops.domain.Interactor.getAllShops.GetAllShopsInteractorFakeImpl
import com.rodrigolc.madridshops.domain.Interactor.SuccessCompletion
import com.rodrigolc.madridshops.domain.model.Shops


class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App", "onCreate")

        val allShopsInteractor = GetAllShopsInteractorFakeImpl()
        allShopsInteractor.execute(
                success = object: SuccessCompletion<Shops>{
                    override fun successCompletion(shops: Shops) {
                        Log.d("Shops", "Count: " + shops.count())

                    }

                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                    }
                }
        )

    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

}
