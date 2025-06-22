package com.supdevinci.fizzquiz.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.supdevinci.fizzquiz.data.RetrofitInstance
import com.supdevinci.fizzquiz.data.database.QuizDatabase
import com.supdevinci.fizzquiz.data.store.User
import com.supdevinci.fizzquiz.models.api.Category
import com.supdevinci.fizzquiz.models.database.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val categoryApi = RetrofitInstance.quizApi
    private val userDao = QuizDatabase.getDatabase(application).userDao()

    private val _categoriesState = MutableStateFlow<List<Category>?>(null)
    val categoriesState: StateFlow<List<Category>?> = _categoriesState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext

    val lastCategoryId: StateFlow<Int?> =
        User.getLastCategoryId(application.applicationContext)
            .stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun saveLastCategory(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        User.saveLastCategoryId(getApplication(), id)
    }

    fun createUser(username: String) = viewModelScope.launch(Dispatchers.IO) {
        if (username.isBlank()) return@launch

        // TODO: Handle errors
        val existingUser = userDao.getByUsername(username = username)

        if (existingUser == null) {
            val newUser = UserEntity(
                username = username,
                createdAt = Date(),
                updatedAt = Date()
            )
            userDao.insert(newUser)
        }

        setUsername(username = username)
    }

    val savedUsername: StateFlow<String?> = User.getUserName(context = context).stateIn(
        viewModelScope,
        SharingStarted.Lazily, null
    )

    fun setUsername(username: String) =
        viewModelScope.launch(Dispatchers.IO) {
            User.saveUsername(
                context = context,
                username = username
            )
        }

    fun getCategories() {
        viewModelScope.launch {
            try {
                val response = categoryApi.getCategories()

                val categories = response?.trivia_categories

                if (categories != null) _categoriesState.value = categories
                else _errorState.value = "No categories found"

            } catch (e: Exception) {
                print(e)
                _errorState.value = "Error: ${e.message}"
            }
        }
    }
}