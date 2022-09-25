package com.quattroSoft.contactAppV4.presenter

import androidx.lifecycle.LiveData
import com.quattroSoft.contactAppV4.data.model.request.LoginModel
import com.quattroSoft.contactAppV4.data.model.response.Message

interface LoginViewModel {


    val loginLiveData  : LiveData<Unit>
    val messageLiveData : LiveData<Message>
    val loadingLiveData : LiveData<Boolean>

    fun onLoginPressed(user : LoginModel)





}