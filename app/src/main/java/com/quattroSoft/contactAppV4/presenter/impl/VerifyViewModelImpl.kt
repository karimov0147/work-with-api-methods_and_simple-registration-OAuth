package com.quattroSoft.contactAppV4.presenter.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quattroSoft.contactAppV4.data.model.request.VerifyModel
import com.quattroSoft.contactAppV4.data.model.response.RegisterResponseModel
import com.quattroSoft.contactAppV4.domain.repository.AuthRepository
import com.quattroSoft.contactAppV4.presenter.VerifyViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VerifyViewModelImpl @Inject constructor(
    private var repository: AuthRepository
) : ViewModel() , VerifyViewModel {



    override val verifyLiveData: MutableLiveData<Unit> = MutableLiveData<Unit>()
    override val onErrorLiveData : MutableLiveData<String> = MutableLiveData()
    override val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData(false)


    override fun verifyData(user : VerifyModel) {
        loadingLiveData.value = true
        repository.verifyUser(user)
            .filter { it.code == 200 }
            .onEach { verifyLiveData.value = Unit }
            .onEach { loadingLiveData.value = false }
            .launchIn(viewModelScope)
    }
//
//        if (response?.message != null){
//            onErrorLiveData.value = response.message
//        }else if(response?.token != null){
//            verifyLiveData.value = Unit
//        }



}