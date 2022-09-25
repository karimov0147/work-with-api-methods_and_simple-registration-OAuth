package com.quattroSoft.contactAppV4.presenter.impl

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quattroSoft.contactAppV4.data.source.local.LocalStorage
import com.quattroSoft.contactAppV4.domain.repository.AuthRepository
import com.quattroSoft.contactAppV4.presenter.StartViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StartViewModelImpl @Inject constructor(
    val localStorage: LocalStorage,
    val repository: AuthRepository
) : StartViewModel , ViewModel() {

    override val navigateToDashboard: MutableLiveData<Unit> = MutableLiveData()
    override val navigateToAuth: MutableLiveData<Unit> = MutableLiveData()



    init {

        viewModelScope.launch {
            delay(1000)
//            Log.d("TTTT" , "start")
//            Log.d("TTTT" , repository.getToken()+ "")
        if (repository.getToken().isNullOrEmpty()){
//            Log.d("TTTT" ,"to Auth")
            navigateToAuth.value = Unit

        } else {
//            Timber.d("SALOM")
           // Log.d("TTTT" ,"to Dashboard")
            navigateToDashboard.value = Unit
        }

        }
    }




}