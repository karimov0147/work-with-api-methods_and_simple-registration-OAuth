package com.quattroSoft.contactAppV4.presenter

import androidx.lifecycle.LiveData

interface StartViewModel {

//    fun innit()

    val navigateToDashboard : LiveData<Unit>

    val navigateToAuth : LiveData<Unit>


}