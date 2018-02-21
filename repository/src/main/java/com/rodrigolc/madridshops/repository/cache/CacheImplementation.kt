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
    val ddbb = cacheDBHelper()

    override fun getAllShoptivities(success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var shoptivities = ShoptivityDAO(ddbb).query()
            DispatchOnMainThreadRun(Runnable {
                if (shoptivities.count() > 0) {
                    ddbb.close()
                    success(shoptivities)
                } else {
                    ddbb.close()
                    error("No shoptivities")
                }
            })
        }).run()
    }

    override fun getAllShoptivitiesForType(type: String, success: (shoptivities: List<ShoptivityEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var shoptivities = ShoptivityDAO(ddbb).queryType(type)
            DispatchOnMainThreadRun(Runnable {
                if (shoptivities.count() > 0) {
                    ddbb.close()
                    success(shoptivities)
                } else {
                    ddbb.close()
                    error("No shoptivities")
                }
            })
        }).run()
    }

    override fun saveAllShoptivities(type: String, shoptivities: List<ShoptivityEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                shoptivities.forEach { ShoptivityDAO(ddbb).insert(type, it) }

                DispatchOnMainThreadRun(Runnable {
                    ddbb.close()
                    success()
                })
            } catch(e: Exception) {
                DispatchOnMainThreadRun(Runnable {
                    ddbb.close()
                        error("Error saving/inserting shoptivities")
                })
            }

        }).run()
    }



    override fun deleteAllShoptivities(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var successDeleting = ShoptivityDAO(ddbb).deleteAll()
            DispatchOnMainThreadRun(Runnable {
                if (successDeleting) {
                    ddbb.close()
                    success()
                } else {
                    ddbb.close()
                    error("Error deleting")
                }
            })
        }).run()
    }

    private fun cacheDBHelper(): DBHelper {
        return buildDBHelper(context.get()!!, "MadridLife.sqlite", 1)
    }
}