package com.example.taskmanager.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun TaskDropdownMenu(
    onDismiss: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    DropdownMenu(
        expanded = true,
        onDismissRequest = { onDismiss() }
    ) {
        DropdownMenuItem(
            text = { Text("Editar") },
            trailingIcon = { Icon(Icons.Rounded.Edit, contentDescription = "Editar Task") },
            onClick = { onEditClick() }
        )
        DropdownMenuItem(
            text = { Text("Deletar") },
            trailingIcon = { Icon(Icons.Rounded.Delete, contentDescription = "Deletar Task") },
            onClick = { onDeleteClick() }
        )
    }
}