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

        /*repository.getAllShoptivities(success = {
            val activitiesList: Shoptivities = entityMapper(it)
            success.successCompletion(activitiesList)
        }, error = {
            error(it)
        })*/
    }

    override fun executeForType(type: String, success: SuccessCompletion<Shoptivities>, error: ErrorCompletion) {
        repository.getAllShoptivitiesForType(type, success = {
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
        val shoptivity = Shoptivity(
                it.id.toInt(),
                it.name,
                it.address,
                it.description_en,
                it.img, it.logo,
                it.openingHoursEn,
                parseStringToDouble(it.longitude),
                parseStringToDouble(it.latitude),
                //"shop")
                it.type.toString())
        Log.d("CORIO", "GetAllShopInteImpl" + shoptivity.type)
        tempList.add(shoptivity)
    }

    val shoptivities = Shoptivities(tempList)
    return shoptivities
}

/*private fun activitiesEntityMapper(list: List<ShoptivityEntity>): Shoptivities {
    val tempList = ArrayList<Shoptivity>()

    list.forEach {
        val shoptivity = Shoptivity(
                it.id.toInt(),
                it.name,
                it.address,
                it.description_en,
                it.img, it.logo,
                it.openingHoursEn,
                parseStringToDouble(it.longitude),
                parseStringToDouble(it.latitude),
                "activity")
        tempList.add(shoptivity)
    }

    val shoptivities = Shoptivities(tempList)
    return shoptivities
}*/

/*private fun shoptivitiesEntityMapper(type: String, list: List<ShoptivityEntity>): Shoptivities {
    val tempList = ArrayList<Shoptivity>()

    list.forEach {
        val shoptivity = Shoptivity(
                it.id.toInt(),
                it.name,
                it.address,
                it.description_en,
                it.img, it.logo,
                it.openingHoursEn,
                parseStringToDouble(it.longitude),
                parseStringToDouble(it.latitude),
                type)
        tempList.add(shoptivity)
    }

    val shoptivities = Shoptivities(tempList)
    return shoptivities
}*/

private fun parseStringToDouble(value: String): Double? {
    var coordinate: Double? = null

    val parsedString: String = value.replace(",", "").replace(" ", "")

    try {
        coordinate = parsedString.toDouble()
    } catch (e: Exception) {
        Log.d("PARSE ERROR", "💩 Error parsing to double: " + value.toString())
    }

    return coordinate
}