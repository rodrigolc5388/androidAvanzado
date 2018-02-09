package com.rodrigolc.madridshops.repository.cache

import android.content.Context
import com.rodrigolc.madridshops.repository.db.DBHelper
import com.rodrigolc.madridshops.repository.db.buildDBHelper
import com.rodrigolc.madridshops.repository.db.dao.ShoptivityDAO
import com.rodrigolc.madridshops.repository.model.ShoptivityEntity
import com.rodrigolc.madridshops.repository.thread.DispatchOnMainThreadRun
import java.lang.ref.WeakReference


internal class CacheImpl(context: Context): Cache {

    val context = WeakReference<Context>(context)

    override fun getAllShops(success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var shops = ShoptivityDAO(cacheDBHelper()).query()
            DispatchOnMainThreadRun(Runnable {
                if (shops.count() > 0) {
                    success(shops)
                } else {
                    error("No shops")
                }
            })
        }).run()
    }

    override fun saveAllShops(shoptivities: List<ShoptivityEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                shoptivities.forEach { ShoptivityDAO(cacheDBHelper()).insert(it) }

                DispatchOnMainThreadRun(Runnable {
                    success()
                })
            } catch(e: Exception) {
                DispatchOnMainThreadRun(Runnable {
                        error("Error inserting shoptivities")
                })
            }

        }).run()
    }



    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var successDeleting = ShoptivityDAO(cacheDBHelper()).deleteAll()
            DispatchOnMainThreadRun(Runnable {
                if (successDeleting) {
                    success()
                } else {
                    error("Error deleting")
                }
            })
        }).run()
    }

    private fun cacheDBHelper(): DBHelper {
        return buildDBHelper(context.get()!!, "MadridShops.sqlite", 1)
    }
}