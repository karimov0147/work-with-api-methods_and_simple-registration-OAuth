package com.quattroSoft.contactAppV4.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactEntity (
    @PrimaryKey
    val id :Int,
    val firstName : String,
    val lastName : String,
    val phone : String
)