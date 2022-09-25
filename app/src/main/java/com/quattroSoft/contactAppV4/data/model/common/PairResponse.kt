package com.quattroSoft.contactAppV4.data.model.common

import com.quattroSoft.contactAppV4.data.model.response.ResponseModel

data class PairResponse(
    var code : Int,
    var array : List<ResponseModel>
)