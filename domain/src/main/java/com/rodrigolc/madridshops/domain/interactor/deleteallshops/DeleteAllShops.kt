package com.rodrigolc.madridshops.domain.Interactor.deleteallshops

import com.rodrigolc.madridshops.domain.Interactor.CodeClosure
import com.rodrigolc.madridshops.domain.Interactor.ErrorClosure


interface  DeleteAllShops {
    fun execute(success: CodeClosure, error: ErrorClosure)
}