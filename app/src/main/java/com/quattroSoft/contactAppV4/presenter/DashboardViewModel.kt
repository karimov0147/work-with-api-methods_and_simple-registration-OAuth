package com.quattroSoft.contactAppV4.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quattroSoft.contactAppV4.data.model.common.ContactModel
import com.quattroSoft.contactAppV4.data.model.request.ModelForAdding
import com.quattroSoft.contactAppV4.data.model.request.ModelForEditing

interface DashboardViewModel {


    //get all data
    val loadContactsLiveData : LiveData<List<ContactModel>>
    val errorLiveData :  MutableLiveData<Unit>
    fun getAllContacts()
    val loadingLiveData : MutableLiveData<Boolean>

    //edit contact
    fun editContact(data : ModelForEditing)

    //fun addContact
    fun addContact(data : ModelForAdding)

    //deleting
    fun deleteContact(id : Int)
}