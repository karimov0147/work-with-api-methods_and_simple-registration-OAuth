package com.quattroSoft.contactAppV4.data.model.response

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("id")
    var id : Int,
    @SerializedName("firstName")
    val firstName : String,
    @SerializedName("lastName")
    val lastName : String,
    @SerializedName("phone")
    val phone : String
)