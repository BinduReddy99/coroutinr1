package com.binduinfo.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.binduinfo.myapplication.R
import com.binduinfo.myapplication.data.db.AppDatabase
import com.binduinfo.myapplication.data.entity.ContactDetails
import com.binduinfo.myapplication.data.repositry.ContactDetailsRepository
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class RoomActivity : AppCompatActivity() {
    lateinit var db: AppDatabase
    lateinit var repository: ContactDetailsRepository
    lateinit var factory: RoomActivityViewModelFactory
    lateinit var viewModel: RoomActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        db = AppDatabase(this)
        repository = ContactDetailsRepository(db)
        factory = RoomActivityViewModelFactory(db, repository)
        viewModel = ViewModelProvider(this, factory).get(RoomActivityViewModel::class.java)
        save_btn.setOnClickListener {
            sendData()
        }
        update.setOnClickListener {
            updateContact()
        }

        CoroutineScope(Main).launch {
            viewModel.getDate().await().observe(this@RoomActivity, Observer {
                Log.d("contactList===", it.toString())
            })
        }
    }

    private fun updateContact() {
        CoroutineScope(Main).launch {
            viewModel.updateContact(id.text.toString().toInt(), name.text.toString(), mobileNumber.text.toString())
        }
    }

    private fun sendData(){
        Log.d("clicked clicked", "Clicked")
        CoroutineScope(Main).launch {
            val ret =   viewModel.insertOperation(ContactDetails(name = name.text.toString(),phone=mobileNumber.text.toString())).await()
            Log.d("clicked clicked", ret.toString())
        }
    }
    private fun deleteContact(){
        CoroutineScope(Main).launch {
            
        }
    }
}
