package com.rodrigolc.madridshops

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.rodrigolc.madridshops.repository.model.ShoptivityEntity
import com.rodrigolc.madridshops.repository.model.ShoptivitiesResponseEntity
import com.rodrigolc.madridshops.repository.network.json.JsonEntitiesParser
import com.rodrigolc.madridshops.util.ReadJsonFile
import org.junit.Assert.*
import org.junit.Test

class JSONParsingTests {
    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parse_correctly() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shop.json")
        assertTrue(false == shopsJson.isEmpty())

        val parser = JsonEntitiesParser()
        val shop = parser.parse<ShoptivityEntity>(shopsJson)

        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.name)
    }

    @Test
    @Throws(Exception::class)
    fun given_invalid_string_containing_json_with_wrong_latitude_it_parses_correctly() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shopWrongLatitude.json")
        assertTrue(false == shopsJson.isEmpty())

        // parsing

        val parser = JsonEntitiesParser()
        var shoptivity: ShoptivityEntity
        try {
            shoptivity = parser.parse<ShoptivityEntity>(shopsJson)
        } catch (e: InvalidFormatException) {
            shoptivity = ShoptivityEntity(1,1,"Parsing failed","", 10f.toString(), 11f.toString(), "", "", "", "")
        }
        assertNotNull(shoptivity)
        assertEquals("Parsing failed", shoptivity.name)
        assertEquals(10f, shoptivity.latitude.toFloat(), 0.1f)
    }



    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parse_correctly_all_shops() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shops.json")
        assertTrue(false == shopsJson.isEmpty())

        val parser = JsonEntitiesParser()
        val responseEntity = parser.parse<ShoptivitiesResponseEntity>(shopsJson)

        assertNotNull(responseEntity)
        assertEquals(6, responseEntity.result.count())
        assertEquals("Cortefiel - Preciados", responseEntity.result[0].name)

    }
}