package com.example.taskmanager.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.components.Grid
import com.example.taskmanager.components.GridList
import com.example.taskmanager.components.TaskItemCard

@Composable
fun ListTaskScreen(modifier: Modifier = Modifier) {
    val localData = com.example.taskmanager.SharedPreferences(LocalContext.current)
    val taskList = List(50) { "Task $it" }
    var gridMode by remember {
        val storedValue = localData.get("gridMode")
        mutableIntStateOf(storedValue?.toIntOrNull() ?: 160)
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Modo de Visualização: ", fontSize = 12.sp,
                modifier = Modifier.weight(0.50f)
            )
            IconButton(
                onClick = {
                    gridMode = 160
                    localData.save("gridMode", gridMode.toString())
                },
                modifier = Modifier.weight(0.25f).size(24.dp)
            ) {
                Icon(
                    imageVector = Grid,
                    contentDescription = "Dois itens listados por linha"
                )
            }
            IconButton(
                onClick = {
                    gridMode = 320
                    localData.save("gridMode", gridMode.toString())
                },
                modifier = Modifier.weight(0.25f).size(24.dp)
            ) {
                Icon(
                    imageVector = GridList,
                    contentDescription = "Um item listado por linha"
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = gridMode.dp),
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
