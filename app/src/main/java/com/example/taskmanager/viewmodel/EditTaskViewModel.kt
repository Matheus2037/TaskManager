package com.example.taskmanager.viewmodel

import android.util.Log
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.Constants
import com.example.taskmanager.SharedPreferences
import com.example.taskmanager.data.TaskDatabase
import com.example.taskmanager.data.TaskEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditTaskViewModel(val localData: SharedPreferences, private val localdb: TaskDatabase, private val taskId: Long) : ViewModel() {

    private val _title = MutableStateFlow( localData.get(Constants.TITLE) )
    val title : StateFlow<String?> = _title

    private val _description = MutableStateFlow( localData.get(Constants.DESCRIPTION) )
    val description : StateFlow<String?> = _description

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet: StateFlow<Boolean> = _showBottomSheet

    init {
        viewModelScope.launch {
            val task = localdb.taskDao().getTaskById(taskId)
            task?.let {
                _title.value = it.title
                _description.value = it.content
            }
        }
    }

    fun editTask() {

        viewModelScope.launch {
            val updatedTask = TaskEntity(
                id = taskId,
                title = _title.value?:"",
                content = _description.value?:""
            )
            localdb.taskDao().update(updatedTask)
        }
    }

    fun setTitle(value: String){
        _title.value = value
    }

    fun setDescription(value: String){
        _description.value = value
    }

    fun setShowBottomSheet(show: Boolean) {
        _showBottomSheet.value = show
    }
}