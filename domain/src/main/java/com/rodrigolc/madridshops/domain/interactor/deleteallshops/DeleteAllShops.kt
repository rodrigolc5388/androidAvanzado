package com.rodrigolc.madridshops.domain.interactor.deleteallshops

import com.rodrigolc.madridshops.domain.interactor.CodeClosure
import com.rodrigolc.madridshops.domain.interactor.ErrorClosure


interface  DeleteAllShops {
    fun execute(success: CodeClosure, error: ErrorClosure)
}