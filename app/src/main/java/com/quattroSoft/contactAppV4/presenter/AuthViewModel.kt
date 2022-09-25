package com.quattroSoft.contactAppV4.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quattroSoft.contactAppV4.data.model.request.RegisterRequestModel

interface AuthViewModel {

    val toLoginLiveData : LiveData<Unit>

    val toVerifyLiveData : LiveData<Map<String , String>>

    val loadingLiveData : MutableLiveData<Boolean>

    fun toLoginScreen()

    fun toVerifyScreen(user : RegisterRequestModel)



}