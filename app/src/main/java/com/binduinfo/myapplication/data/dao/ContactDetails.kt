package com.binduinfo.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.binduinfo.myapplication.data.entity.ContactDetails


@Dao
interface ContactDetails {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveContacts(contact: ContactDetails): Long

    @Query("UPDATE ContactDetails  SET name=:name, phone=:number WHERE id =:id")
    suspend fun updateContacts(id: Int, name: String, number: String)

    @Query("SELECT * FROM ContactDetails")
     fun getData():LiveData<List<ContactDetails>>

    @Query("DELETE  FROM ContactDetails WHERE id =:id ")
     fun  delData(id: Long) : Long

    @Query("DELETE FROM ContactDetails")
    fun deleteAll()
    fun delData()


}
