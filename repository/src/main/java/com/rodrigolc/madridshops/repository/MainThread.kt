package com.rodrigolc.madridshops.repository.thread

import android.os.Handler
import android.os.Looper


fun DispatchOnMainThreadRun(codeToRun: Runnable){
    val uiHandler = Handler(Looper.getMainLooper())
    uiHandler.post(codeToRun)
}
