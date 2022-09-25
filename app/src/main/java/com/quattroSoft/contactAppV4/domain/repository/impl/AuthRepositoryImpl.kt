package com.quattroSoft.contactAppV4.domain.repository.impl

import android.util.Log
import com.quattroSoft.contactAppV4.data.model.request.LoginModel
import com.quattroSoft.contactAppV4.data.model.request.RegisterRequestModel
import com.quattroSoft.contactAppV4.data.model.request.VerifyModel
import com.quattroSoft.contactAppV4.data.model.response.Message
import com.quattroSoft.contactAppV4.data.model.response.RegisterResponseModel
import com.quattroSoft.contactAppV4.data.source.local.LocalStorage
import com.quattroSoft.contactAppV4.data.source.remote.ContactApi
import com.quattroSoft.contactAppV4.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    val contactApi: ContactApi,
    val localStorage: LocalStorage
) : AuthRepository {



    override fun registerUser(userData: RegisterRequestModel): Flow<Message> = flow  {
        var result = contactApi.registerUser(userData)
        if (result.isSuccessful && result.code() ==200){
            emit(Message("${result.body().toString()}" , 200))
        }else
            emit(Message("${result.body().toString()}" , 400))

    }

    override fun verifyUser(userData: VerifyModel): Flow<Message> = flow {
      var result = contactApi.verifyUser(userData)
        if (result.isSuccessful){
            localStorage.setToken(result.body()?.token)
            if (!localStorage.getToken().isNullOrEmpty()){
                emit(Message("Sucsess" , 200))
            }else{
                emit(Message("${result.body().toString()}" , 400))
            }
        }
    }

    override  fun loginUser(userData: LoginModel) :   Flow<Message> =  flow {


        var result = contactApi.loginUserSuspend(userData)
        if (result.isSuccessful){
            localStorage.setToken(result.body()?.token)
            if (!localStorage.getToken().isNullOrEmpty()){
                emit(Message("Sucsess" , 200))
            }else{
                emit(Message("${result.body().toString()}" , 400))
            }



        }
















//        var model : RegisterResponseModel? = null
//        var complate = false
//
//        contactApi.loginUser(userData).enqueue(object : Callback<RegisterResponseModel>{
//            override fun onResponse(
//                call: Call<RegisterResponseModel>,
//                response: Response<RegisterResponseModel>
//            ) {
//                if (response.isSuccessful && response.body() != null) {
//                    model = response.body()
//                    if(model?.token != null){
//                        localStorage.setToken(model?.token)
//                    }
////                    localStorage.setToken(response.body()?.token)
//                    complate = true
//                }
//            }
//
//            override fun onFailure(call: Call<RegisterResponseModel>, t: Throwable) {
//
//            }
//
//        })
//        return complate

    }

    override fun getToken(): String? {
        return localStorage.getToken()
    }

}