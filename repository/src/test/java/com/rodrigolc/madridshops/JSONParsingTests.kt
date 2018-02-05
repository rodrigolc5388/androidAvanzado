package com.rodrigolc.madridshops

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.rodrigolc.madridshops.repository.model.ShopEntity
import com.rodrigolc.madridshops.repository.model.ShopsResponseEntity
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
        val shop = parser.parse<ShopEntity>(shopsJson)

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
        var shop: ShopEntity
        try {
            shop = parser.parse<ShopEntity>(shopsJson)
        } catch (e: InvalidFormatException) {
            shop = ShopEntity(1,1,"Parsing failed","", 10f.toString(), 11f.toString(), "", "", "", "")
        }
        assertNotNull(shop)
        assertEquals("Parsing failed", shop.name)
        assertEquals(10f, shop.latitude.toFloat(), 0.1f)
    }



    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parse_correctly_all_shops() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shops.json")
        assertTrue(false == shopsJson.isEmpty())

        val parser = JsonEntitiesParser()
        val responseEntity = parser.parse<ShopsResponseEntity>(shopsJson)

        assertNotNull(responseEntity)
        assertEquals(6, responseEntity.result.count())
        assertEquals("Cortefiel - Preciados", responseEntity.result[0].name)

    }
}