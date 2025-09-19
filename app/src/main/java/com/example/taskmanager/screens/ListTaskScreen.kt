package com.example.taskmanager.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanager.Constants

@Composable
fun ListTaskScreen(paddingValues: PaddingValues, modifier: Modifier = Modifier) {
    val localData = com.example.taskmanager.SharedPreferences(LocalContext.current)

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(text = localData.get(Constants.TITLE)?: "")
        Text(text = localData.get(Constants.DESCRIPTION)?: "")
    }
}


@Preview(showBackground = true)
@Composable
fun ListTaskScreenPreview(){
    ListTaskScreen(paddingValues = PaddingValues())
}
