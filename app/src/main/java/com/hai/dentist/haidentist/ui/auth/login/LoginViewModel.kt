package com.hai.dentist.haidentist.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hai.dentist.haidentist.data.local.UserPreference
import com.hai.dentist.haidentist.data.model.UserDataModel
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun saveusers(pref: UserPreference,user:UserDataModel) {
        viewModelScope.launch {
             pref.saveUser(user)
        }

    }

}