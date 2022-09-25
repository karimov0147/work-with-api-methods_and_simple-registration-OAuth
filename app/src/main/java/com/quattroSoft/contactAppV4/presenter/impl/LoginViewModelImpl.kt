package com.quattroSoft.contactAppV4.presenter.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quattroSoft.contactAppV4.data.model.request.LoginModel
import com.quattroSoft.contactAppV4.data.model.response.Message
import com.quattroSoft.contactAppV4.domain.repository.AuthRepository
import com.quattroSoft.contactAppV4.presenter.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    private val authRepository: AuthRepository

) : LoginViewModel , ViewModel() {

    override val loginLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val messageLiveData: MutableLiveData<Message> = MutableLiveData()
    override val loadingLiveData: MutableLiveData<Boolean>  = MutableLiveData(false)

    override fun onLoginPressed(user : LoginModel) {

        loadingLiveData.value = true

        authRepository.loginUser(user)
            .onEach { messageLiveData.value = it }
            .filter { it.code == 200 }
            .onEach { loadingLiveData.value = false }
            .onEach { loginLiveData.value = Unit }
            .launchIn(viewModelScope)






    }

}