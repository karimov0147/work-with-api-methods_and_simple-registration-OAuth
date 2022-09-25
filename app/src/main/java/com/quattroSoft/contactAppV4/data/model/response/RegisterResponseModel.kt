package com.quattroSoft.contactAppV4.data.model.response

import com.google.gson.annotations.SerializedName

data class RegisterResponseModel(
    @SerializedName("token")
    val token : String?,
    @SerializedName("phone")
    val phone : String?
)
