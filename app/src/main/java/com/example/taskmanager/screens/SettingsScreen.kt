package com.example.taskmanager.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskmanager.Constants
import com.example.taskmanager.data.TaskDatabase

@Composable
fun SettingsScreen(modifier: Modifier = Modifier, localdb: TaskDatabase){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = Constants.CONFIGURATION_NAV_TITLE)
    }
}