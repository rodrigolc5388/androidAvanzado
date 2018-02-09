package com.rodrigolc.madridshops.domain.interactor.getAllShopshops

import android.content.Context
import android.util.Log
import com.rodrigolc.madridshops.domain.interactor.ErrorCompletion
import com.rodrigolc.madridshops.domain.interactor.SuccessCompletion
import com.rodrigolc.madridshops.domain.interactor.getAllShops.GetAllShoptivitiesInteractor
import com.rodrigolc.madridshops.domain.model.Shoptivities
import com.rodrigolc.madridshops.domain.model.Shoptivity
import com.rodrigolc.madridshops.repository.Repository
import com.rodrigolc.madridshops.repository.RepositoryImpl
import com.rodrigolc.madridshops.repository.model.ShoptivityEntity
import java.lang.ref.WeakReference

class GetAllShoptivitiesInteractorImplementation(context: Context): GetAllShoptivitiesInteractor {
    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get()!!)

    override fun execute(success: SuccessCompletion<Shoptivities>, error: ErrorCompletion) {
        repository.getAllShoptivities(success = {
            val shoptivitiesList: Shoptivities = entityMapper(it)
            success.successCompletion(shoptivitiesList)
        }, error = {
            error(it)
        })

    }
}

private fun entityMapper(list: List<ShoptivityEntity>): Shoptivities {
    val tempList = ArrayList<Shoptivity>()

    list.forEach {
        val shoptivity = Shoptivity(it.id.toInt(),
                it.name,
                it.address,
                it.description_en,
                it.img, it.logo,
                it.openingHoursEn,
                parseStringToFloat(it.longitude)!!,
                parseStringToFloat(it.latitude)!!,
                it.type!!)
        tempList.add(shoptivity)
    }

    val shoptivities = Shoptivities(tempList)
    return shoptivities
}

private fun parseStringToFloat(value: String): Float? {
    var coordinate: Float? = null

    val parsedString: String = value.replace(",", "").replace(" ", "")

    try {
        coordinate = parsedString.toFloat()
    } catch (e: Exception) {
        Log.d("PARSE ERROR", "💩 Error parsing lat/long string to float.")
    }

    return coordinate
}