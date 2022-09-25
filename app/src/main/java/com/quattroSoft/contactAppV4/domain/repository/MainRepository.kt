package com.quattroSoft.contactAppV4.domain.repository

import com.quattroSoft.contactAppV4.data.model.common.ContactModel
import com.quattroSoft.contactAppV4.data.model.common.PairResponse
import com.quattroSoft.contactAppV4.data.model.request.ModelForAdding
import com.quattroSoft.contactAppV4.data.model.request.ModelForEditing
import com.quattroSoft.contactAppV4.data.model.response.ResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface  MainRepository {

    fun addContact(user : ModelForAdding) : Flow<ResponseModel>

    fun editContact(user : ModelForEditing) : Flow<ResponseModel>

    fun deleteContact(id  : Int) : Flow<ResponseModel>

    fun getAllContacts() : Flow<PairResponse>
}