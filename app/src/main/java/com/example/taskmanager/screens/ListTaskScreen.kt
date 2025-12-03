package com.example.taskmanager.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmanager.components.DeleteAlertDialog
import com.example.taskmanager.components.Grid
import com.example.taskmanager.components.GridList
import com.example.taskmanager.components.TaskItemCard
import com.example.taskmanager.components.TaskPartialBottomSheet
import com.example.taskmanager.viewmodel.ListTaskScreenViewModel
import com.example.taskmanager.viewmodel.ListTaskScreenViewModelFactory

@Composable
fun ListTaskScreen(modifier: Modifier = Modifier) {
    val localData = com.example.taskmanager.SharedPreferences(LocalContext.current)
    val listTaskScreenViewModel: ListTaskScreenViewModel = viewModel(factory = ListTaskScreenViewModelFactory(localData))
    val taskList = List(50) { "Task $it" }

    val gridMode by listTaskScreenViewModel.gridMode.collectAsState()
    val showDeleteAlertDialog by listTaskScreenViewModel.showDeleteAlertDialog.collectAsState(false)
    val showBottomSheet by listTaskScreenViewModel.showBottomSheet.collectAsState(false)
    val activeMenuIndex by listTaskScreenViewModel.activeMenuIndex.collectAsState()

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Modo de Visualização: ", fontSize = 12.sp,
                modifier = Modifier.weight(0.50f)
            )
            IconButton(
                onClick = {
                    listTaskScreenViewModel.setGridMode(160)
                },
                modifier = Modifier
                    .weight(0.25f)
                    .size(24.dp)
            ) {
                Icon(
                    imageVector = Grid,
                    contentDescription = "Dois itens listados por linha"
                )
            }
            IconButton(
                onClick = {
                    listTaskScreenViewModel.setGridMode(320)
                },
                modifier = Modifier
                    .weight(0.25f)
                    .size(24.dp)
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            items(taskList.size) { index ->
                TaskItemCard(
                    isMenuVisible = activeMenuIndex == index,
                    onMenuClick = { listTaskScreenViewModel.onMenuClick(index) },
                    onEditClick = {
                        listTaskScreenViewModel.setShowBottomSheet(true)
                        listTaskScreenViewModel.closeMenus()
                    },
                    onDeleteClick = {
                        listTaskScreenViewModel.setShowDeleteAlertDialog(true)
                        listTaskScreenViewModel.closeMenus()
                    }
                )
            }
        }
    }
    Box (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp), contentAlignment = Alignment.BottomEnd){
        FloatingActionButton(onClick = { /* TODO */ }) {
            Text(text = "+")
        }
    }

    if (showDeleteAlertDialog) {
        DeleteAlertDialog(
            onDismiss = { listTaskScreenViewModel.setShowDeleteAlertDialog(false) },
            onConfirmation = {
                listTaskScreenViewModel.deleteTask()
            }
        )
    }

    if (showBottomSheet) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TaskPartialBottomSheet(
                onDismiss = { listTaskScreenViewModel.setShowBottomSheet(false) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListTaskScreenPreview(){
    ListTaskScreen()
}
