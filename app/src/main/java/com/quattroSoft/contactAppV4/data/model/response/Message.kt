package com.quattroSoft.contactAppV4.data.model.response

import com.google.gson.annotations.SerializedName

data class Message (
    @SerializedName("message")
    var message: String,
    var code: Int =  200
)