package com.example.data.proto

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.domain.model.UserProfile
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(val context: Context) {
    private val USER_INFO_KEY = stringPreferencesKey("user_info")
    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "user_preferences"
    )
    suspend fun saveUserProfile(userProfile: UserProfile) {
        context.preferencesDataStore.edit { preferences ->
            preferences[USER_INFO_KEY] = Gson().toJson(userProfile)
        }
    }

    fun getUserProfile(): Flow<UserProfile?> {
        return context.preferencesDataStore.data
            .map { preferences ->
                preferences[USER_INFO_KEY]?.let {
                    Gson().fromJson(it, UserProfile::class.java)
                } ?: run { null }
            }
    }
}
