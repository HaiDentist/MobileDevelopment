package com.hai.dentist.haidentist.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hai.dentist.haidentist.data.local.UserPreference
import com.hai.dentist.haidentist.data.model.Disease

class HomeViewModel : ViewModel() {

    fun loaddisease(pref: UserPreference): LiveData<Disease> {
        return pref.getDisease().asLiveData()
    }
    // TODO: Implement the ViewModel
}