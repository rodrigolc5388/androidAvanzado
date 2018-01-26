package com.rodrigolc.madridshops.domain.Interactor

import com.rodrigolc.madridshops.domain.model.Shops

typealias CodeClosure = () -> Unit
typealias SuccessClosure = (shops: Shops) -> Unit
typealias ErrorClosure = (msg: String) -> Unit
typealias Variant = Any