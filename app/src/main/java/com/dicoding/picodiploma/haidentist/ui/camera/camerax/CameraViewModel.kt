package com.dicoding.picodiploma.haidentist.ui.camera.camerax

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.haidentist.data.local.UserPreference
import com.dicoding.picodiploma.haidentist.data.model.Disease
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