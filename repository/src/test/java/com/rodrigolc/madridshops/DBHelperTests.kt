package com.rodrigolc.madridshops

import org.junit.Test

import com.rodrigolc.madridshops.repository.db.convert
import org.junit.Assert.*

class DBHelperTests {
    @Test
    @Throws(Exception::class)
    fun given_false_converts_into_0() {
        assertEquals(0, convert(false).toLong())
    }
}