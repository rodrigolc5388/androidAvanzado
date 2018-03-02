package com.rodrigolc.madridshops.repository

import android.content.Context
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.rodrigolc.madridshops.repository.cache.Cache
import com.rodrigolc.madridshops.repository.cache.CacheImpl
import com.rodrigolc.madridshops.repository.model.ShoptivitiesResponseEntity
import com.rodrigolc.madridshops.repository.model.ShoptivityEntity
import com.rodrigolc.madridshops.repository.network.GetJsonManager
import com.rodrigolc.madridshops.repository.network.GetJsonManagerVolleyImpl
import com.rodrigolc.madridshops.repository.network.json.JsonEntitiesParser
import com.rodrigolc.madridshops.utils.SectionType
import madridshops.rodrigolc.com.repository.BuildConfig
import java.lang.ref.WeakReference


class RepositoryImpl(context: Context): Repository {


    private val weakContext = WeakReference<Context>(context)
    private val cache: Cache = CacheImpl(weakContext.get()!!)
    private val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get() !!)

    override fun getAllShoptivities(success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // Read all Shoptivities from cache
        cache.getAllShoptivities(success = {
            // if there's Shoptivities in cache --> return Shoptivities
            success(it)

        }, error = {
            // if no Shoptivities in cache --> network
            populateCache(SectionType.SHOP, success, error)
            populateCache(SectionType.ACTIVITY, success, error)

        })
    }

    override fun getAllShoptivitiesForType(type: SectionType, success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit) {

        cache.getAllShoptivitiesForType(type, success = {
            success(it)
        }, error = {
            error("Error getting Shoptivities for type: " + type)
        })
    }

    override fun deleteAllShoptivities(success: () -> Unit, error: (errorMessage: String) -> Unit) {

        cache.deleteAllShoptivities(success, error)
    }

    private fun populateCache(type: SectionType, success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit) {

        var url : String = when(type){
            SectionType.SHOP -> BuildConfig.MADRID_SHOPS_SERVER_URL
            SectionType.ACTIVITY -> BuildConfig.MADRID_ACTIVITIES_SERVER_URL
        }


        /*if (type == SectionType.SHOP){
            url = BuildConfig.MADRID_SHOPS_SERVER_URL
        } else if (type == SectionType.ACTIVITY){
            url = BuildConfig.MADRID_ACTIVITIES_SERVER_URL
        }*/


        jsonManager.execute(url,
                success = object : SuccessCompletion<String> {
                    override fun successCompletion(e: String) {
                        val parser = JsonEntitiesParser()
                        val shoptivitiesResponse: ShoptivitiesResponseEntity

                        try {
                            shoptivitiesResponse = parser.parse<ShoptivitiesResponseEntity>(e)
                        } catch (e: InvalidFormatException) {
                            error("ERROR PARSING SHOPTIVITIES")
                            return
                        }
                        // store result in cache
                        cache.saveAllShoptivities(type, shoptivitiesResponse.result, success = {
                            success(shoptivitiesResponse.result)
                        }, error = {
                            error("Something happened on the way to Shoptivities heaven!")
                        })
                    }
                }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
            }
        })
    }

}