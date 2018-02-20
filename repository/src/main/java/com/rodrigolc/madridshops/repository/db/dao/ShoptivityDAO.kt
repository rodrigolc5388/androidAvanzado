package com.rodrigolc.madridshops.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.rodrigolc.madridshops.repository.db.DBConstants
import com.rodrigolc.madridshops.repository.db.DBHelper
import com.rodrigolc.madridshops.repository.model.ShoptivityEntity

internal class ShoptivityDAO(val dbHelper: DBHelper): DAOPersistable<ShoptivityEntity>{

    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase

    override fun insert(type: String, element: ShoptivityEntity): Long {
        var id: Long = 0

        id = dbReadWriteConnection.insert(DBConstants.TABLE_SHOPTIVITIES, null, contentValues(type, element))
        return id
    }

    override fun delete(element: ShoptivityEntity): Long {
        if (element.databaseId < 1) {
            return 0
        }

        return delete(element.databaseId)
    }

    override fun delete(id: Long): Long {
        return dbReadWriteConnection.delete(
                DBConstants.TABLE_SHOPTIVITIES,
                DBConstants.KEY_SHOPTIVITY_DATABASE_ID + " = ?",
                arrayOf(id.toString())
                ).toLong()
    }

    override fun deleteAll(): Boolean {
        return dbReadWriteConnection.delete(
                DBConstants.TABLE_SHOPTIVITIES,
                null,
                null
        ).toLong() >= 0
    }

    override fun query(id: Long): ShoptivityEntity {
        val cursor = queryCursor(id)

        cursor.moveToFirst()

        return entityFromCursor(cursor)!!
    }

    override fun query(): List<ShoptivityEntity> {
        val queryResult = ArrayList<ShoptivityEntity>()

        val cursor = dbReadOnlyConnection.query(DBConstants.TABLE_SHOPTIVITIES,
                DBConstants.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstants.KEY_SHOPTIVITY_NAME)

        while (cursor.moveToNext()){
            val se = entityFromCursor(cursor)
            queryResult.add(se!!)
        }

        return queryResult
    }

    override fun queryType(type: String): List<ShoptivityEntity> {
        val queryResult = ArrayList<ShoptivityEntity>()

        val cursor = dbReadOnlyConnection.query(DBConstants.TABLE_SHOPTIVITIES,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_SHOPTIVITY_TYPE + " = ?",
                arrayOf(type),
                "",
                "",
                DBConstants.KEY_SHOPTIVITY_NAME)

        while (cursor.moveToNext()){
            val se = entityFromCursor(cursor)
            Log.d("CORIO DAO", "" + se?.name)
            queryResult.add(se!!)
        }

        return queryResult
    }

    override fun queryCursor(id: Long): Cursor {
        val cursor = dbReadOnlyConnection.query(DBConstants.TABLE_SHOPTIVITIES,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_SHOPTIVITY_DATABASE_ID + " = ?",
                arrayOf(id.toString()),
                "",
                "",
                DBConstants.KEY_SHOPTIVITY_DATABASE_ID)

        return cursor
    }

    // Usar element.databaseId o el parámetro id??
    override fun update(id: Long, element: ShoptivityEntity): Long {
        val numberOfRecordsUpdated = dbReadWriteConnection.update(DBConstants.TABLE_SHOPTIVITIES,
                contentValues(element.type.toString(), element),
                DBConstants.KEY_SHOPTIVITY_DATABASE_ID + " = ?",
                arrayOf(element.databaseId.toString())).toLong()
        //(id.toString())

        return numberOfRecordsUpdated
    }


    fun contentValues(type: String, shoptivityEntity: ShoptivityEntity): ContentValues {
        val content = ContentValues()

        content.put(DBConstants.KEY_SHOPTIVITY_ID_JSON, shoptivityEntity.id)
        content.put(DBConstants.KEY_SHOPTIVITY_NAME, shoptivityEntity.name)
        content.put(DBConstants.KEY_SHOPTIVITY_IMAGE_URL, shoptivityEntity.img)
        content.put(DBConstants.KEY_SHOPTIVITY_LOGO_IMAGE_URL, shoptivityEntity.logo)
        content.put(DBConstants.KEY_SHOPTIVITY_ADDRESS, shoptivityEntity.address)
        content.put(DBConstants.KEY_SHOPTIVITY_URL, shoptivityEntity.url)
        content.put(DBConstants.KEY_SHOPTIVITY_DESCRIPTION_EN, shoptivityEntity.description_en)
        content.put(DBConstants.KEY_SHOPTIVITY_DESCRIPTION_ES, shoptivityEntity.description_es)
        content.put(DBConstants.KEY_SHOPTIVITY_LATITUDE, shoptivityEntity.latitude)
        content.put(DBConstants.KEY_SHOPTIVITY_LONGITUDE, shoptivityEntity.longitude)
        content.put(DBConstants.KEY_SHOPTIVITY_OPENING_HOURS_EN, shoptivityEntity.openingHoursEn)
        content.put(DBConstants.KEY_SHOPTIVITY_OPENING_HOURS_ES, shoptivityEntity.openingHoursEs)
        content.put(DBConstants.KEY_SHOPTIVITY_TYPE, type)

        return content
    }

    fun entityFromCursor(cursor: Cursor): ShoptivityEntity? {
        if(cursor.isAfterLast || cursor.isBeforeFirst){
            return null
        }

        return ShoptivityEntity(
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOPTIVITY_DATABASE_ID)),
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOPTIVITY_ID_JSON)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOPTIVITY_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOPTIVITY_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOPTIVITY_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOPTIVITY_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOPTIVITY_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOPTIVITY_DESCRIPTION_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOPTIVITY_DESCRIPTION_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOPTIVITY_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOPTIVITY_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOPTIVITY_OPENING_HOURS_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOPTIVITY_OPENING_HOURS_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOPTIVITY_TYPE))
        )
    }


}
