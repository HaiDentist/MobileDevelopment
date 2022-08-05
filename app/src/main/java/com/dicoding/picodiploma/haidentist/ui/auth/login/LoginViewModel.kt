package com.dicoding.picodiploma.haidentist.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.haidentist.data.local.UserPreference
import com.dicoding.picodiploma.haidentist.data.model.Disease
import com.dicoding.picodiploma.haidentist.data.model.UserDataModel
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun saveusers(pref: UserPreference,user:UserDataModel) {
        viewModelScope.launch {
             pref.saveUser(user)
        }

    }

}