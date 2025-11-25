package com.example.taskmanager.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun DeleteAlertDialog(
    onDismiss: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            Button(onClick = { onConfirmation() }) {
                Text(text = "Confirmar")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text(text = "Cancelar")
            }
        },
        text = { Text("Deseja realmente cancelar essa Task ?") }
    )
}