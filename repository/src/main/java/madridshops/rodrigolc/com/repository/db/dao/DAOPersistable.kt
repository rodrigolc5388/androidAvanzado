package madridshops.rodrigolc.com.repository.db.dao

import android.database.Cursor

interface DAOReadOperations<T>{
    fun query(id: Int): T
    fun query(): List<T>
    fun queryCursor(): Cursor
}


interface  DAOWriteOprations<T>{
    fun insert()
    fun update()
    fun delete()
    fun deleteAll()
}

interface DAOPersistable<T>: DAOReadOperations<T>, DAOWriteOprations<T>

