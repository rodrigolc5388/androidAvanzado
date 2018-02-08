package com.rodrigolc.madridshops.repository.db

internal object DBConstants {
    val TABLE_SHOPTIVITIES = "SHOPTIVITIES"

    // Table field constants
    val KEY_SHOPTIVITY_DATABASE_ID = "_id"
    val KEY_SHOPTIVITY_ID_JSON = "ID_JSON"
    val KEY_SHOPTIVITY_NAME = "NAME"
    val KEY_SHOPTIVITY_IMAGE_URL = "IMAGE_URL"
    val KEY_SHOPTIVITY_LOGO_IMAGE_URL = "LOGO_IMAGE_URL"
    val KEY_SHOPTIVITY_ADDRESS = "ADDRESS"
    val KEY_SHOPTIVITY_URL = "URL"
    val KEY_SHOPTIVITY_DESCRIPTION_EN = "DESCRIPTION_EN"
    val KEY_SHOPTIVITY_DESCRIPTION_ES = "DESCRIPTION_ES"
    val KEY_SHOPTIVITY_LATITUDE = "LATITUDE"
    val KEY_SHOPTIVITY_LONGITUDE = "LONGITUDE"
    val KEY_SHOPTIVITY_OPENING_HOURS_EN = "OPENING_HOURS_EN"
    val KEY_SHOPTIVITY_OPENING_HOURS_ES = "OPENING_HOURS_ES"
    val KEY_SHOPTIVITY_TYPE = "TYPE"

    val ALL_COLUMNS = arrayOf(
            KEY_SHOPTIVITY_DATABASE_ID,
            KEY_SHOPTIVITY_ID_JSON,
            KEY_SHOPTIVITY_NAME,
            KEY_SHOPTIVITY_IMAGE_URL,
            KEY_SHOPTIVITY_LOGO_IMAGE_URL,
            KEY_SHOPTIVITY_ADDRESS,
            KEY_SHOPTIVITY_URL,
            KEY_SHOPTIVITY_DESCRIPTION_EN,
            KEY_SHOPTIVITY_DESCRIPTION_ES,
            KEY_SHOPTIVITY_LATITUDE,
            KEY_SHOPTIVITY_LONGITUDE,
            KEY_SHOPTIVITY_OPENING_HOURS_EN,
            KEY_SHOPTIVITY_OPENING_HOURS_ES,
            KEY_SHOPTIVITY_TYPE)

    val SQL_SCRIPT_CREATE_SHOPTIVITIES_TABLE = (
            "create table " + TABLE_SHOPTIVITIES
                    + "( "
                    + KEY_SHOPTIVITY_DATABASE_ID + " integer primary key autoincrement, "
                    + KEY_SHOPTIVITY_ID_JSON + " integer, "
                    + KEY_SHOPTIVITY_NAME + " text not null,"
                    + KEY_SHOPTIVITY_IMAGE_URL + " text, "
                    + KEY_SHOPTIVITY_LOGO_IMAGE_URL + " text, "
                    + KEY_SHOPTIVITY_ADDRESS + " text,"
                    + KEY_SHOPTIVITY_URL + " text,"
                    + KEY_SHOPTIVITY_LATITUDE + " real,"
                    + KEY_SHOPTIVITY_LONGITUDE + " real, "
                    + KEY_SHOPTIVITY_DESCRIPTION_EN + " text, "
                    + KEY_SHOPTIVITY_DESCRIPTION_ES + " text, "
                    + KEY_SHOPTIVITY_OPENING_HOURS_EN + " text "
                    + KEY_SHOPTIVITY_OPENING_HOURS_ES + " text "
                    + KEY_SHOPTIVITY_TYPE + " text "
                    + ");")

    val DROP_DATABASE_SCRIPTS = ""

    val CREATE_DATABASE_SCRIPTS = arrayOf(SQL_SCRIPT_CREATE_SHOPTIVITIES_TABLE)
}
