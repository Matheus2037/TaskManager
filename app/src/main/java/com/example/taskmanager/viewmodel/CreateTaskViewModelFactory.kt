package com.example.taskmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.SharedPreferences

class CreateTaskViewModelFactory(private val localData: SharedPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateTaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateTaskViewModel(localData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}