package com.rodrigolc.madridshops.repository.cache

import android.content.Context
import com.rodrigolc.madridshops.repository.db.DBHelper
import com.rodrigolc.madridshops.repository.db.buildDBHelper
import com.rodrigolc.madridshops.repository.db.dao.ShopDAO
import com.rodrigolc.madridshops.repository.model.ShopEntity
import com.rodrigolc.madridshops.repository.thread.DispatchOnMainThreadRun
import java.lang.ref.WeakReference


internal class CacheImpl(context: Context): Cache {

    val context = WeakReference<Context>(context)

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var shops = ShopDAO(cacheDBHelper()).query()
            DispatchOnMainThreadRun(Runnable {
                if (shops.count() > 0) {
                    success(shops)
                } else {
                    error("No shops")
                }
            })
        }).run()
    }

    override fun saveAllShops(shops: List<ShopEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                shops.forEach { ShopDAO(cacheDBHelper()).insert(it) }

                DispatchOnMainThreadRun(Runnable {
                    success()
                })
            } catch(e: Exception) {
                DispatchOnMainThreadRun(Runnable {
                        error("Error inserting shops")
                })
            }

        }).run()
    }



    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var successDeleting = ShopDAO(cacheDBHelper()).deleteAll()
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