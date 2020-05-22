package com.binduinfo.myapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.binduinfo.myapplication.data.db.AppDatabase
import com.binduinfo.myapplication.data.entity.ContactDetails
import com.binduinfo.myapplication.data.repositry.ContactDetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RoomActivityViewModel(val db:AppDatabase, val repository: ContactDetailsRepository): ViewModel() {

    suspend fun insertOperation(contactDetails: ContactDetails): Deferred<Long>{
        return CoroutineScope(IO).async {
            repository.insertResponse(contactDetails)
        }
    }

    suspend fun updateContact(id:Int, name:String, phone:String){
        CoroutineScope(IO).async {
                repository.updateContact(id, name, phone)
        }
    }

    suspend fun getDate(): Deferred<LiveData<List<ContactDetails>>>{
        return CoroutineScope(IO).async {
            repository.getContact()
        }
    }
    suspend fun delData(id: Int = 1){
         CoroutineScope(IO) .async {
            repository.deleteContact(id)
        }
    }

}