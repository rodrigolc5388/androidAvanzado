package com.rodrigolc.madridshops.domain.interactor.internetstatus

import android.content.Context
import com.rodrigolc.madridshops.domain.interactor.CodeClosure
import com.rodrigolc.madridshops.domain.interactor.ErrorClosure


interface InternetStatusInteractor {
    fun execute(context: Context, success: CodeClosure, error: ErrorClosure)
}
