package com.rodrigolc.madridshops.repository.network

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.rodrigolc.madridshops.repository.ErrorCompletion
import com.rodrigolc.madridshops.repository.SuccessCompletion
import java.lang.ref.WeakReference

internal class GetJsonManagerVolleyImpl(context: Context): GetJsonManager {

    var weakContext: WeakReference<Context> = WeakReference(context)
    var requestQueue: RequestQueue? = null


    override fun execute(url: String, success: SuccessCompletion<String>, error: ErrorCompletion) {

        // create request (success, failure)
        val request = StringRequest(url,
                Response.Listener {
                    success.successCompletion(it)
                },
                Response.ErrorListener {
                    error.errorCompletion(it.localizedMessage)
                })


        // add request to queue
        requestQueue().add(request)
    }

    // get request queue
    fun requestQueue(): RequestQueue {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(weakContext.get())
        }

        return requestQueue!!
    }
}
