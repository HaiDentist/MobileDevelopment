package com.hai.dentist.haidentist.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hai.dentist.haidentist.data.local.UserPreference
import kotlinx.coroutines.launch

class OnBoardingViewModel :ViewModel() {

    fun getuser(preferences: UserPreference) {
        viewModelScope.launch {
            preferences.getUser()
        }
    }
}