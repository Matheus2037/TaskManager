package com.example.taskmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.SharedPreferences
import com.example.taskmanager.data.TaskDatabase

class EditTaskViewModelFactory(private val localData: SharedPreferences, private val localdb: TaskDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditTaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditTaskViewModel(localData, localdb) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}