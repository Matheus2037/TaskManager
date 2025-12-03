package com.example.taskmanager.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.taskmanager.Constants
import com.example.taskmanager.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreateTaskViewModel (val localData: SharedPreferences) : ViewModel(){

    private val _title = MutableStateFlow( localData.get(Constants.TITLE) )
    val title : StateFlow<String?> = _title

    private val _description = MutableStateFlow( localData.get(Constants.DESCRIPTION) )
    val description : StateFlow<String?> = _description

    fun createTask() {
        localData.save(Constants.TITLE, _title.value?:"")
        localData.save(Constants.DESCRIPTION, _description.value?:"")

        Log.i(
            "Informacao",
            "CreateTaskScreen: Titulo: ${localData.get(Constants.TITLE)} Descricao: ${localData.get(Constants.DESCRIPTION)}"
        )
    }

    fun setTitle(value: String){
        _title.value = value
    }

    fun setDescription(value: String){
        _description.value = value
    }
}