package com.example.taskmanager.viewmodel

import android.util.Log
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.lifecycle.ViewModel
import com.example.taskmanager.Constants
import com.example.taskmanager.SharedPreferences
import com.example.taskmanager.data.TaskDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EditTaskViewModel(val localData: SharedPreferences, localdb: TaskDatabase) : ViewModel() {

    private val _title = MutableStateFlow( localData.get(Constants.TITLE) )
    val title : StateFlow<String?> = _title

    private val _description = MutableStateFlow( localData.get(Constants.DESCRIPTION) )
    val description : StateFlow<String?> = _description

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet: StateFlow<Boolean> = _showBottomSheet

    fun editTask() {
        localData.save(Constants.TITLE, _title.value?:"")
        localData.save(Constants.DESCRIPTION, _description.value?:"")

        Log.i(
            "Edicao de TASK",
            "PartialBottomSheet: Titulo: ${localData.get(Constants.TITLE)} Descricao: ${localData.get(
                Constants.DESCRIPTION)}"
        )
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