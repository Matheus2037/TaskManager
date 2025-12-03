package com.example.taskmanager.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmanager.SharedPreferences
import com.example.taskmanager.viewmodel.EditTaskViewModel
import com.example.taskmanager.viewmodel.EditTaskViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskPartialBottomSheet( onDismiss: () -> Unit ) {
    val localData = SharedPreferences(LocalContext.current)
    val editTaskViewModel: EditTaskViewModel = viewModel(factory = EditTaskViewModelFactory(localData))

    val title by editTaskViewModel.title.collectAsState()
    val description by editTaskViewModel.description.collectAsState()

    val showBottomSheet by editTaskViewModel.showBottomSheet.collectAsState()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    LaunchedEffect(showBottomSheet) {
        if (showBottomSheet) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(),
        sheetState = sheetState,
        onDismissRequest = { onDismiss() }
    ) {
        Text(
            "Editando Tarefa: ",
            modifier = Modifier.padding(16.dp)
        )
        OutlinedTextField(
            value = title?: "",
            onValueChange = {editTaskViewModel.setTitle(it)},
            label = { Text("Titulo: ")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = description?: "",
            onValueChange = {editTaskViewModel.setDescription(it)},
            label = { Text("Descrição: ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                editTaskViewModel.editTask()
                onDismiss()
            }
        ) {
            Text("Confirmar Alteração")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TaskPartialBottomSheetPreview(){
    TaskPartialBottomSheet(onDismiss = {})
}