package com.rodrigolc.madridshops.domain.interactor.getAllShopshops

import android.content.Context
import com.rodrigolc.madridshops.domain.interactor.ErrorCompletion
import com.rodrigolc.madridshops.domain.interactor.SuccessCompletion
import com.rodrigolc.madridshops.domain.interactor.getAllShops.GetAllShopsInteractor
import com.rodrigolc.madridshops.domain.model.Shop
import com.rodrigolc.madridshops.domain.model.Shops
import com.rodrigolc.madridshops.repository.Repository
import com.rodrigolc.madridshops.repository.RepositoryImpl
import com.rodrigolc.madridshops.repository.model.ShoptivityEntity
import java.lang.ref.WeakReference

class GetAllShopsInteractorImplementation(context: Context): GetAllShopsInteractor {
    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get()!!)

    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        repository.getAllShops(success = {
            val shops: Shops = entityMapper(it)
            success.successCompletion(shops)
        }, error = {
            error(it)
        })

    }
}

private fun entityMapper(list: List<ShoptivityEntity>): Shops {
    val tempList = ArrayList<Shop>()

    list.forEach {
        val shop = Shop(it.id.toInt(), it.name, it.address)
        tempList.add(shop)
    }

    val shops = Shops(tempList)
    return shops
}