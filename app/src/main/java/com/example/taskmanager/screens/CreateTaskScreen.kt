package com.example.taskmanager.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmanager.Constants
import com.example.taskmanager.SharedPreferences
import com.example.taskmanager.viewmodel.CreateTaskViewModel
import com.example.taskmanager.viewmodel.CreateTaskViewModelFactory


@Composable
fun CreateTaskScreen(modifier: Modifier = Modifier){
    val localData = SharedPreferences(LocalContext.current)
    val createTaskViewModel: CreateTaskViewModel = viewModel(factory = CreateTaskViewModelFactory(localData))

    val title by createTaskViewModel.title.collectAsState()
    val description by createTaskViewModel.description.collectAsState()
    
    Column(
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
    ) {

        OutlinedTextField(
            value = title?:"",
            onValueChange = {createTaskViewModel.setTitle(it)},
            label = { Text(Constants.LABEL_TITLE)},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = description?:"",
            onValueChange = {createTaskViewModel.setDescription(it)},
            label = { Text(Constants.LABEL_DESCRIPTION)},
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            contentAlignment = Alignment.Center
        ){

            Button(onClick = {
                createTaskViewModel.createTask()
            }) {
                Text(Constants.CREATE_BUTTON)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CreateTaskScreenPreview(){
    CreateTaskScreen()
}
