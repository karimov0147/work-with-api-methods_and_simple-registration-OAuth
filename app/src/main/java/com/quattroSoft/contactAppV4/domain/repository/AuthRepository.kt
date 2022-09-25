package com.quattroSoft.contactAppV4.domain.repository

import com.quattroSoft.contactAppV4.data.model.request.LoginModel
import com.quattroSoft.contactAppV4.data.model.request.RegisterRequestModel
import com.quattroSoft.contactAppV4.data.model.request.VerifyModel
import com.quattroSoft.contactAppV4.data.model.response.Message
import com.quattroSoft.contactAppV4.data.model.response.RegisterResponseModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun registerUser(userData : RegisterRequestModel) : Flow<Message>

    fun verifyUser(userData : VerifyModel) : Flow<Message>

    fun loginUser(userData: LoginModel) : Flow<Message>

    fun getToken()  : String?




}