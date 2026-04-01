package com.example.taskmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.Constants
import com.example.taskmanager.SharedPreferences
import com.example.taskmanager.data.TaskDatabase
import com.example.taskmanager.data.TaskEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListTaskScreenViewModel (val localData: SharedPreferences, private val localdb: TaskDatabase) : ViewModel() {

    private val _gridMode = MutableStateFlow(
        localData.get(Constants.GRID_MODE) ?.toIntOrNull() ?: 160
    )
    val gridMode : StateFlow<Int> = _gridMode

    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks : StateFlow<List<TaskEntity>> = _tasks


    private val _activeMenuIndex = MutableStateFlow<Int?>(null)
    val activeMenuIndex: StateFlow<Int?> = _activeMenuIndex



    private val _showDeleteAlertDialog = MutableStateFlow(false)
    val showDeleteAlertDialog : StateFlow<Boolean> = _showDeleteAlertDialog



    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet : StateFlow<Boolean> = _showBottomSheet

    fun loadTask() {
        viewModelScope.launch {
            _tasks.value = localdb.taskDao().getAll()
        }
    }



    //Funções para lógica de alterar Grid da listagem
    fun setGridMode(newMode: Int){
        _gridMode.value = newMode

        localData.save(Constants.GRID_MODE, newMode.toString())
    }
    //Fim da lógica de alterar Grid da listagem




    //Funções para lógica de deletar TASK
    fun deleteTask() {
        localData.delete(Constants.TITLE)
        localData.delete(Constants.DESCRIPTION)
        _showDeleteAlertDialog.value = false
    }
    fun setShowDeleteAlertDialog(value: Boolean) {
        _showDeleteAlertDialog.value = value
    }
    //Fim da lógica de deletar TASK




    //Funções para lógica de editar TASK
    fun setShowBottomSheet(value: Boolean){
        _showBottomSheet.value = value
    }
    //Fim da lógica de edição de TASK



    //Funções para lógica de menu da TASK
    fun onMenuClick(index: Int) {
        _activeMenuIndex.value = if (_activeMenuIndex.value == index) null else index
    }
    fun closeMenus() {
        _activeMenuIndex.value = null
    }
    //Fim da lógica de MENU


}