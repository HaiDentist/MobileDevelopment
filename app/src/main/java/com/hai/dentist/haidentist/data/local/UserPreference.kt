package com.hai.dentist.haidentist.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.hai.dentist.haidentist.data.model.Disease
import com.hai.dentist.haidentist.data.model.UserDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    //Get User Value
    fun getUser(): Flow<UserDataModel> {
        return dataStore.data.map { preferences ->
            UserDataModel(
                preferences[TOKEN_KEY] ?: "",
                preferences[STATE_KEY] ?: false
            )
        }
    }

    fun getDisease():Flow<Disease>{
       return dataStore.data.map { preferences ->
           Disease(preferences[DISEASE_KEY] ?: "" )
       }
    }

    //Save User Login
    suspend fun saveUser(user: UserDataModel) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = user.token
            preferences[STATE_KEY] = user.isLogin
        }
    }

     suspend fun saveDisease(disease: Disease) {
        dataStore.edit { preferences ->
            preferences[DISEASE_KEY] = disease.disease
        }
    }



    //Clear Login State (Logout)
    suspend fun clearUser() {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = ""
            preferences[STATE_KEY] = false
            preferences[DISEASE_KEY]= ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val TOKEN_KEY = stringPreferencesKey("token")
        private val STATE_KEY = booleanPreferencesKey("state")
        private val DISEASE_KEY = stringPreferencesKey("disease")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}