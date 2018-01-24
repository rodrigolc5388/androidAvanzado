package com.rodrigolc.madridshops.domain.Interactor

import com.rodrigolc.madridshops.domain.model.Shops


interface GetAllShopsInteractor {
    fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion)
}
