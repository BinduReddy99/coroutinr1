package com.binduinfo.myapplication.data.repositry

import androidx.lifecycle.LiveData
import com.binduinfo.myapplication.data.db.AppDatabase
import com.binduinfo.myapplication.data.entity.ContactDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ContactDetailsRepository(val db: AppDatabase) {

    suspend fun insertResponse(contactDetails: ContactDetails): Long{
        return withContext(IO){
            db.getContactDetails().saveContacts(contactDetails)
        }
    }

    suspend fun updateContact(id:Int, name: String, phone:String){
        withContext(IO){
            db.getContactDetails().updateContacts(id, name, phone)
        }
    }

    suspend fun getContact(): LiveData<List<ContactDetails>>{
        return withContext(IO){
            db.getContactDetails().getData()
        }
    }
}