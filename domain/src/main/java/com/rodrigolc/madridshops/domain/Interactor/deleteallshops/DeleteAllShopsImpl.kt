package com.rodrigolc.madridshops.domain.Interactor.deleteallshops

import android.content.Context
import com.rodrigolc.madridshops.domain.Interactor.CodeClosure
import com.rodrigolc.madridshops.domain.Interactor.ErrorClosure
import com.rodrigolc.madridshops.repository.RepositoryImpl
import java.lang.ref.WeakReference

class DeleteAllShopsImpl(context: Context): DeleteAllShops {
    val weakContext = WeakReference<Context>(context)

    override fun execute(success: CodeClosure, error: ErrorClosure) {
        val repository = RepositoryImpl(weakContext.get()!!)

        repository.deleteAllShops(success, error)
    }
}