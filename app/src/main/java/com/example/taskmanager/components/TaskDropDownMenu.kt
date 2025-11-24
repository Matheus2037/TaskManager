package com.example.taskmanager.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskDropdownMenu(
    onDismiss: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        DropdownMenu(
            expanded = true,
            onDismissRequest = { onDismiss() }
        ) {
            DropdownMenuItem(
                text = { Text("Editar Task") },
                onClick = { onEditClick() }
            )
            DropdownMenuItem(
                text = { Text("Deletar Task") },
                onClick = { onDeleteClick() }
            )
        }
    }
}