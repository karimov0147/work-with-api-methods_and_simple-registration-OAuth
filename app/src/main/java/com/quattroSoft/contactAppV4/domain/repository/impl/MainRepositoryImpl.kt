package com.quattroSoft.contactAppV4.domain.repository.impl

import android.util.Log
import com.quattroSoft.contactAppV4.data.model.common.ContactModel
import com.quattroSoft.contactAppV4.data.model.common.PairResponse
import com.quattroSoft.contactAppV4.data.model.request.ModelForAdding
import com.quattroSoft.contactAppV4.data.model.request.ModelForEditing
import com.quattroSoft.contactAppV4.data.model.response.ResponseModel
import com.quattroSoft.contactAppV4.data.source.local.LocalStorage
import com.quattroSoft.contactAppV4.data.source.local.dao.ContactDao
import com.quattroSoft.contactAppV4.data.source.remote.ContactApi
import com.quattroSoft.contactAppV4.domain.repository.MainRepository
import com.quattroSoft.contactAppV4.utills.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    val localStorage: LocalStorage,
    val contactApi: ContactApi,
    val contactDao: ContactDao
) : MainRepository {
    override fun addContact(user: ModelForAdding): Flow<ResponseModel>  = flow {
        var result = contactApi.addContact(localStorage.getToken().toString() , user)

        if (result.isSuccessful){
            emit(ResponseModel(0, result.body()?.firstName?: " " , "internet" ,  "09809"))
        }




    }

    override fun editContact(user: ModelForEditing): Flow<ResponseModel> = flow {
        var result = contactApi.editContact(localStorage.getToken().toString() , user)
        if (result.isSuccessful){
//            var model : ContactModel = result.body().toModel(ResponseModel(0 , "not data" , "internet" ,  "09809"))
                emit(ResponseModel(result.body()?.id ?: (-1), "not data" , "internet" ,  "09809"))

        }
    }

    override fun deleteContact(id: Int): Flow<ResponseModel> = flow {
        var result = contactApi.deleteContact(localStorage.getToken().toString() , id)
        if (result.isSuccessful){
            emit(ResponseModel(result.body()?.id ?: (-1), "not data" , "internet" ,  "09809"))
        }

    }

    override  fun getAllContacts(): Flow<PairResponse> =  flow {
        var result = contactApi.getAllContacts(localStorage.getToken().toString())
        lateinit var response: PairResponse
        if (result.isSuccessful){
            var array :List<ResponseModel> = result.body() ?: arrayListOf(ResponseModel(0 , "not data" , "internet" ,  "09809"))
            response = PairResponse(200 , array)
            emit(response)
        }
        else emit(PairResponse(400 , arrayListOf()))

    }
}



