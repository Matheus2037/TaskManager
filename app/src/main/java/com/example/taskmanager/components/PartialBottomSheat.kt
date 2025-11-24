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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.taskmanager.Constants
import com.example.taskmanager.SharedPreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskPartialBottomSheet( onDismiss: () -> Unit ) {
    val localData = SharedPreferences(LocalContext.current)
    var title by remember { mutableStateOf(localData.get(Constants.TITLE)) }
    var description by remember { mutableStateOf(localData.get(Constants.DESCRIPTION)) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

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
            onValueChange = {title = it},
            label = { Text("Titulo: ")},
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        OutlinedTextField(
            value = description?: "",
            onValueChange = {description = it},
            label = { Text("Descrição: ") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                localData.save(Constants.TITLE, title?: "")
                localData.save(Constants.DESCRIPTION, description?: "")
                onDismiss()
            }
        ) {
            Text("Confirmar Alteração")
        }
    }
}