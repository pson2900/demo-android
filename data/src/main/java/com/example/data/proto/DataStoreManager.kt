package com.example.data.proto

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.domain.model.Authentication
import com.example.domain.model.UserProfile
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class DataStoreManager(private val context: Context) {
    private val USER_INFO_KEY = stringPreferencesKey("user_info")
    private val AUTH_KEY = stringPreferencesKey("navigos_auth_key")

    fun isLogin(): Boolean = runBlocking {
        val auth = getAuth().firstOrNull()
        auth != null
    }

    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "user_preferences"
    )

    suspend fun saveAuth(authentication: Authentication) {
        clearSpecificKey(AUTH_KEY)
        context.preferencesDataStore.edit { preferences ->
            preferences[AUTH_KEY] = Gson().toJson(authentication)
        }
    }

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

    fun getAuth(): Flow<Authentication?> {
        return context.preferencesDataStore.data
            .map { preferences ->
                preferences[AUTH_KEY]?.let {
                    Gson().fromJson(it, Authentication::class.java)
                } ?: run { null }
            }
    }

    suspend fun clearSpecificKey(key: Preferences.Key<String>) {
        withContext(kotlinx.coroutines.Dispatchers.IO) {
            context.preferencesDataStore.edit { preferences ->
                preferences.remove(key)
            }
        }
    }

    suspend fun clearAll() {
        withContext(kotlinx.coroutines.Dispatchers.IO) {
            context.preferencesDataStore.edit { preferences ->
                preferences.clear()
            }
        }
    }
}