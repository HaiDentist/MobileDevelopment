package com.dicoding.picodiploma.haidentist.ui.profile

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.haidentist.data.local.UserPreference
import kotlinx.coroutines.launch

class ProfileViewModel :ViewModel() {


    fun logout(preferences: UserPreference) {
        viewModelScope.launch {
            preferences.clearUser()
        }
    }

}