package com.supdevinci.fizzquiz.data.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

object User {
    private val USERNAME_KEY = stringPreferencesKey("username")
    private val LAST_CATEGORY_KEY = intPreferencesKey("last_category_id")

    fun getUserName(context: Context): Flow<String?> =
        context.dataStore.data.map { preferences -> preferences[USERNAME_KEY] }

    suspend fun saveUsername(context: Context, username: String) =
        context.dataStore.edit { preferences -> preferences[USERNAME_KEY] = username }

    fun getLastCategoryId(context: Context): Flow<Int?> =
        context.dataStore.data.map { prefs -> prefs[LAST_CATEGORY_KEY] }

    suspend fun saveLastCategoryId(context: Context, id: Int) {
        context.dataStore.edit { prefs -> prefs[LAST_CATEGORY_KEY] = id }
    }
}