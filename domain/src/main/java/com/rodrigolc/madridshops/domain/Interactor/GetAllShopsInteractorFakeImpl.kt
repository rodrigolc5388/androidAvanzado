package com.rodrigolc.madridshops.domain.Interactor

import com.rodrigolc.madridshops.domain.model.Shop
import com.rodrigolc.madridshops.domain.model.Shops


class GetAllShopsInteractorFakeImpl: GetAllShopsInteractor {
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
