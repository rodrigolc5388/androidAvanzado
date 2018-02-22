package com.rodrigolc.madridshops.utils

import java.io.Serializable

enum class SectionType(val type: String): Serializable {
    SHOP("shop"),
    ACTIVITY("activity")
}