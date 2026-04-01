package com.example.taskmanager.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.Constants
import com.example.taskmanager.SharedPreferences
import com.example.taskmanager.data.TaskDatabase
import com.example.taskmanager.data.TaskEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateTaskViewModel (val localData: SharedPreferences, private val localdb: TaskDatabase) : ViewModel(){

    private val _title = MutableStateFlow( localData.get(Constants.TITLE) )
    val title : StateFlow<String?> = _title

    private val _description = MutableStateFlow( localData.get(Constants.DESCRIPTION) )
    val description : StateFlow<String?> = _description

    fun createTask() {
        viewModelScope.launch {
            localdb.taskDao().insertAll(TaskEntity(0, _title.value?:"", _description.value?:""))
            Log.i("CreateTaskViewModel", "Task created: ${localdb.taskDao().getAll()}")

        }
    }

    fun setTitle(value: String){
        _title.value = value
    }

    fun setDescription(value: String){
        _description.value = value
    }
}