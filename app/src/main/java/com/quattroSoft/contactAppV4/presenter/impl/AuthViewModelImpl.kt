package com.quattroSoft.contactAppV4.presenter.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quattroSoft.contactAppV4.data.model.request.RegisterRequestModel
import com.quattroSoft.contactAppV4.domain.repository.AuthRepository
import com.quattroSoft.contactAppV4.presenter.AuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject


@HiltViewModel
class AuthViewModelImpl @Inject constructor(
    val authRepository: AuthRepository
)  : ViewModel() , AuthViewModel {


    override val toLoginLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val toVerifyLiveData: MutableLiveData<Map<String,String>> = MutableLiveData()
    override val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun toLoginScreen() {
        toLoginLiveData.value = Unit
    }

    override fun toVerifyScreen(user: RegisterRequestModel) {
        loadingLiveData.value = true
        var number = user.phone
//        var result = authRepository.registerUser(user).message
        authRepository.registerUser(user)
            .filter { it.code == 200 }
            .onEach { loadingLiveData.value = false }
            .onEach { toVerifyLiveData.value = mapOf(number to it.message) }
            .launchIn(viewModelScope)
    }


}