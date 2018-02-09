package com.rodrigolc.madridshops.domain.interactor

import com.rodrigolc.madridshops.domain.model.Shoptivities

typealias CodeClosure = () -> Unit
typealias SuccessClosure = (shoptivities: Shoptivities) -> Unit
typealias ErrorClosure = (msg: String) -> Unit
typealias Variant = Any