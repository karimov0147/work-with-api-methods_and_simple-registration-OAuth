package com.quattroSoft.contactAppV4.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quattroSoft.contactAppV4.data.model.request.VerifyModel

interface VerifyViewModel {

    val verifyLiveData : LiveData<Unit>
    val onErrorLiveData : LiveData<String>
    val loadingLiveData : MutableLiveData<Boolean>

    fun verifyData(user : VerifyModel)



}