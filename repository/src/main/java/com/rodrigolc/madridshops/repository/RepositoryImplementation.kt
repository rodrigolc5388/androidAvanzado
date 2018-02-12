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
            populateCacheWithShops(success, error)
            populateCacheWithActivities(success, error)
        })
    }

    override fun getAllShoptivitiesForType(type: String, success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit) {

        cache.getAllShoptivitiesForType(type, success = {
            success(it)
        }, error = {
            error("Error getting Shoptivities of type: " + type)
        })
    }

    override fun deleteAllShoptivities(success: () -> Unit, error: (errorMessage: String) -> Unit) {

        cache.deleteAllShoptivities(success, error)
    }

    private fun populateCacheWithShops(success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // perform network request
        jsonManager.execute(BuildConfig.MADRID_SHOPS_SERVER_URL, success =  object: SuccessCompletion<String> {
            override fun successCompletion(e: String) {
                val parser = JsonEntitiesParser()
                var shopsResponseEntity: ShoptivitiesResponseEntity
                try {
                    shopsResponseEntity = parser.parse<ShoptivitiesResponseEntity>(e)
                } catch (e: InvalidFormatException) {
                    error("ERROR PARSING SHOPS")
                    return
                }
                // store result in cache
                cache.saveAllShoptivities(shopsResponseEntity.result, success = {
                    success(shopsResponseEntity.result)
                }, error = {
                    error("Something happened on the way to Shops heaven!")
                })
            }
        }, error = object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
            }
        })
    }

    private fun populateCacheWithActivities(success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        jsonManager.execute(BuildConfig.MADRID_ACTIVITIES_SERVER_URL,
                success = object : SuccessCompletion<String> {
                    override fun successCompletion(e: String) {
                        val parser = JsonEntitiesParser()
                        var activitiesResponseEntity: ShoptivitiesResponseEntity
                        try {
                            activitiesResponseEntity = parser.parse<ShoptivitiesResponseEntity>(e)
                        } catch (e: InvalidFormatException) {
                            error("ERROR PARSING ACTIVITIES")
                            return
                        }
                        // store result in cache
                        cache.saveAllShoptivities(activitiesResponseEntity.result, success = {
                            success(activitiesResponseEntity.result)
                        }, error = {
                            error("Something happened on the way to Activities heaven!")
                        })
                    }
                }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
            }
        })
    }

}