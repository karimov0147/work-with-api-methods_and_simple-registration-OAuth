package com.quattroSoft.contactAppV4.presenter.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quattroSoft.contactAppV4.data.model.common.ContactModel
import com.quattroSoft.contactAppV4.data.model.request.ModelForAdding
import com.quattroSoft.contactAppV4.data.model.request.ModelForEditing
import com.quattroSoft.contactAppV4.domain.repository.MainRepository
import com.quattroSoft.contactAppV4.presenter.DashboardViewModel
import com.quattroSoft.contactAppV4.utills.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DashboardViewModelImpl @Inject constructor(
    val repository: MainRepository
) : DashboardViewModel , ViewModel() {
    override var loadContactsLiveData: MutableLiveData<List<ContactModel>> = MutableLiveData()
    override val errorLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun getAllContacts() {
        loadingLiveData.value = true
        repository.getAllContacts()
            .onEach { if (it.code == 200) {
                loadingLiveData.value = false
                loadContactsLiveData.value = it.array.map { it.toModel() } }
                if (it.code == 400 ){
                    loadingLiveData.value = false
                    errorLiveData.value = Unit } }
            .launchIn(viewModelScope)

    }


    override fun editContact(data: ModelForEditing) {
        repository.editContact(data)
            .onEach { if (it.id == -1) errorLiveData.value = Unit }
            .filter { it.id == data.id  }
            .onEach { getAllContacts() }
            .launchIn(viewModelScope)
    }

    override fun addContact(data: ModelForAdding) {
        repository.addContact(data)
            .filter { it.firstName == data.firstName }
            .onEach { getAllContacts() }
            .launchIn(viewModelScope)
    }

    override fun deleteContact(id: Int) {
        repository.deleteContact(id)
            .onEach { if (it.id == -1) errorLiveData.value = Unit }
            .filter { it.id == id  }
            .onEach { getAllContacts() }
            .launchIn(viewModelScope)

    }
}