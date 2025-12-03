package com.example.taskmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.SharedPreferences

class EditTaskViewModelFactory(private val localData: SharedPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditTaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditTaskViewModel(localData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}