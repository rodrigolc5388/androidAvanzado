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

    override fun getAllShops(success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // Read all Shops from cache
        cache.getAllShops(success = {
            // if there's Shops in cache --> return Shops
            success(it)

        }, error = {
            // if no Shops in cache --> network

            populateCache(success, error)
        })

    }

    private fun populateCache(success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // perform network request

        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get() !!)
        jsonManager.execute(BuildConfig.MADRID_SHOPS_SERVER_URL, success =  object: SuccessCompletion<String> {
            override fun successCompletion(e: String) {
                val parser = JsonEntitiesParser()
                var responseEntity: ShoptivitiesResponseEntity
                try {
                    responseEntity = parser.parse<ShoptivitiesResponseEntity>(e)
                } catch (e: InvalidFormatException) {
                    error("ERROR PARSING")
                    return
                }
                // store result in cache
                cache.saveAllShops(responseEntity.result, success = {
                    success(responseEntity.result)
                }, error = {
                    error("Something happened on the way to heaven!")
                })
            }
        }, error = object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
            }
        })
    }



    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {

        cache.deleteAllShops(success, error)
    }
}