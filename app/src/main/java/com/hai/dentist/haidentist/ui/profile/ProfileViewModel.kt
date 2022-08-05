package com.hai.dentist.haidentist.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hai.dentist.haidentist.data.local.UserPreference
import kotlinx.coroutines.launch

class ProfileViewModel :ViewModel() {


    fun logout(preferences: UserPreference) {
        viewModelScope.launch {
            preferences.clearUser()
        }
    }

}