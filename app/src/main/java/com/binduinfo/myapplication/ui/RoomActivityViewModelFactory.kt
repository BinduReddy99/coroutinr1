package com.binduinfo.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.binduinfo.myapplication.data.db.AppDatabase
import com.binduinfo.myapplication.data.entity.ContactDetails
import com.binduinfo.myapplication.data.repositry.ContactDetailsRepository

class RoomActivityViewModelFactory(val db: AppDatabase, val repository: ContactDetailsRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RoomActivityViewModel(db, repository) as T
    }
}