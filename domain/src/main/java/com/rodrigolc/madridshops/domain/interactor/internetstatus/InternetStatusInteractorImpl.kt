package com.rodrigolc.madridshops.domain.interactor.internetstatus

import android.content.Context
import android.net.ConnectivityManager
import com.rodrigolc.madridshops.domain.interactor.CodeClosure
import com.rodrigolc.madridshops.domain.interactor.ErrorClosure

class InternetStatusInteractorImpl : InternetStatusInteractor {
    override fun execute(context: Context, success: CodeClosure, error: ErrorClosure) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected){
            success()
        } else {
            error("Error trying to connect to internet")
        }
    }
}