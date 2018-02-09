package com.rodrigolc.madridshops.domain.interactor.getAllShops




/*class GetAllShoptivitiesInteractorFakeImpl : GetAllShoptivitiesInteractor {

    override fun execute(success: SuccessCompletion<Shoptivities>, error: ErrorCompletion) {
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


    fun createFakeListOfShops(): Shoptivities {
        val list = ArrayList<Shoptivity>()

        for(i in 0..100){
            val shop = Shoptivity(i, "Shoptivity " + i, address = "Address " + i)
            list.add(shop)
        }

        val shops = Shoptivities(list)
        return shops
    }
}*/
