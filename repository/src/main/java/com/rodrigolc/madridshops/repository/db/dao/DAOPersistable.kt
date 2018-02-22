package com.rodrigolc.madridshops.repository.db.dao

import android.database.Cursor
import com.rodrigolc.madridshops.utils.SectionType

interface DAOReadOperations<T>{
    fun query(id: Long): T
    fun query(): List<T>
    fun queryType(type: SectionType): List<T>
    fun queryCursor(id: Long ): Cursor
}


interface DAOWriteOprations<T>{
    fun insert(type: String, element: T): Long
    fun update(id: Long, element: T): Long
    // Deletes the element passed from DB
    fun delete(element: T): Long
    // Deletes the element with ID from DB
    fun delete(id: Long): Long
    fun deleteAll(): Boolean
}

interface DAOPersistable<T>: DAOReadOperations<T>, DAOWriteOprations<T>

