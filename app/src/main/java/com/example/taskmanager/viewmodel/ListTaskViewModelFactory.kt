package com.example.taskmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.SharedPreferences

class ListTaskScreenViewModelFactory(private val localData: SharedPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListTaskScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListTaskScreenViewModel(localData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}