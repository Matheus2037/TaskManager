package com.example.taskmanager.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.taskmanager.Constants
import com.example.taskmanager.data.TaskEntity

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
                Text(text = Constants.CONFIRM)
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text(text = Constants.CANCEL)
            }
        },
        text = { Text(Constants.CONFIRMATION_TEXT) }
    )
}