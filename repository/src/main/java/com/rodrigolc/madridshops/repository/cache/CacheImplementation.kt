package com.rodrigolc.madridshops.repository.cache

import android.content.Context
import com.rodrigolc.madridshops.repository.db.DBHelper
import com.rodrigolc.madridshops.repository.db.buildDBHelper
import com.rodrigolc.madridshops.repository.db.dao.ShopDAO
import com.rodrigolc.madridshops.repository.thread.DispatchOnMainThreadRun
import java.lang.ref.WeakReference


internal class CacheImpl(context: Context): Cache {
    val context = WeakReference<Context>(context)

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