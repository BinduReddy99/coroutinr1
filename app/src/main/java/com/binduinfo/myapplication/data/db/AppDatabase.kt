package com.binduinfo.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.binduinfo.myapplication.data.dao.ContactDetails

private const val DB_NAME = "Contacts.db"
@Database(entities = [com.binduinfo.myapplication.data.entity.ContactDetails::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getContactDetails(): ContactDetails

    companion object {
        @Volatile
        private var instanse: AppDatabase? = null
        private val Lock = Any()
        operator fun invoke(context: Context) = instanse ?: synchronized(Lock) {
            instanse ?:buildBase(context).also {
                instanse = it
            }
        }
        private fun buildBase(context: Context) = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
    }
}