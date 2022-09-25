package com.quattroSoft.contactAppV4.data.source.remote

import com.quattroSoft.contactAppV4.data.model.request.*
import com.quattroSoft.contactAppV4.data.model.response.Message
import com.quattroSoft.contactAppV4.data.model.response.RegisterResponseModel
import com.quattroSoft.contactAppV4.data.model.response.ResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ContactApi {

    @POST("/api/v1/register")
    suspend fun registerUser( @Body user : RegisterRequestModel ) : Response<Message>


    @POST("/api/v1/register/verify")
    suspend fun verifyUser( @Body user : VerifyModel ) : Response<RegisterResponseModel>


    @POST("/api/v1/login")
    suspend fun loginUserSuspend( @Body user : LoginModel ) : Response<RegisterResponseModel>


    @POST("/api/v1/contact")
    suspend fun addContact( @Header("token") token: String, @Body user : ModelForAdding ) : Response<ResponseModel>


//    @Headers("Content-Type: application/json")
    @PUT("/api/v1/contact")
    suspend fun editContact(  @Header("token")token : String , @Body user : ModelForEditing) : Response<ResponseModel>


//    @Headers("Content-Type: application/json", "token : {token}")
    @DELETE("/api/v1/contact")
    suspend fun deleteContact (@Header("token") token : String, @Query("id") id : Int ) : Response<ResponseModel>


    @Headers("Content-type: application/json")
    @GET("/api/v1/contact")
    suspend fun getAllContacts( @Header("token")token : String ) : Response<List<ResponseModel>>

}



