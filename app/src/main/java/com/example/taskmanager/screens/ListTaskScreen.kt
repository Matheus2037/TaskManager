package com.example.taskmanager.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanager.components.TaskItemCard

@Composable
fun ListTaskScreen(modifier: Modifier = Modifier) {
    val localData = com.example.taskmanager.SharedPreferences(LocalContext.current)
    val taskList = List(50) { "Task $it" }

    Column(
        modifier = Modifier
            .padding()
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
            contentPadding = PaddingValues(12.dp),
            modifier = Modifier.fillMaxWidth().padding(12.dp)
        ) {
            items(taskList.size) { index ->
                TaskItemCard()
            }
        }
    }
    Box (modifier = Modifier.fillMaxSize().padding(10.dp), contentAlignment = Alignment.BottomEnd){
        FloatingActionButton(onClick = { /* TODO */ }) {
            Text(text = "+")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListTaskScreenPreview(){
    ListTaskScreen()
}
