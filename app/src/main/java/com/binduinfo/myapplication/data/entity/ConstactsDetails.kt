package com.binduinfo.myapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class ContactDetails(@PrimaryKey(autoGenerate = true) val id:Int = 0,  val name: String, val phone: String)