package com.quattroSoft.contactAppV4.utills

import com.quattroSoft.contactAppV4.data.model.common.ContactModel
import com.quattroSoft.contactAppV4.data.model.response.ResponseModel

fun ResponseModel.toModel() = ContactModel(id , firstName , lastName , phone)