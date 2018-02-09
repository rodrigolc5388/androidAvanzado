package com.rodrigolc.madridshops.domain.interactor.getAllShops

import com.rodrigolc.madridshops.domain.interactor.ErrorClosure
import com.rodrigolc.madridshops.domain.interactor.ErrorCompletion
import com.rodrigolc.madridshops.domain.interactor.SuccessClosure
import com.rodrigolc.madridshops.domain.interactor.SuccessCompletion
import com.rodrigolc.madridshops.domain.model.Shop
import com.rodrigolc.madridshops.domain.model.Shops
import java.util.*




class GetAllShoptivitiesInteractorFakeImpl : GetAllShoptivitiesInteractor {

    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        var allOk = true

        // connect to the repository

        if (allOk) {
            val shops = createFakeListOfShops()

            success.successCompletion(shops)
        } else {
            error.errorCompletion("Error while accessing the Repository")
        }
    }


    fun execute(success: SuccessClosure, error: ErrorClosure) {
        var allOk = true

        // connect to the repository

        if (allOk) {
            val shops = createFakeListOfShops()

            success(shops)
        } else {
            error("Error while accessing the Repository")
        }
    }


    fun createFakeListOfShops(): Shops {
        val list = ArrayList<Shop>()

        for(i in 0..100){
            val shop = Shop(i, "Shop " + i, address = "Address " + i)
            list.add(shop)
        }

        val shops = Shops(list)
        return shops
    }
}
