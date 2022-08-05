package com.hai.dentist.haidentist.ui.camera.camerax

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hai.dentist.haidentist.data.local.UserPreference
import com.hai.dentist.haidentist.data.model.Disease
import kotlinx.coroutines.launch

class CameraViewModel :ViewModel() {

//    fun login(user: UserLoginModel, pref: UserPreference) {
//        viewModelScope.launch {
//            userRepo.authenticate(user, pref).collect{ response ->
//                _loginResponse.value = response
//            }
//        }
//    }

    fun savedisease(disease: Disease, preference: UserPreference) {
        viewModelScope.launch {
            preference.saveDisease(disease)
        }
    }

}