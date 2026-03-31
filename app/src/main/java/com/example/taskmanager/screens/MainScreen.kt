package com.example.taskmanager.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.data.TaskDatabase
import com.example.taskmanager.screens.navigation.BottomNavItem
import com.example.taskmanager.screens.navigation.BottomNavigationBar

private lateinit var localdb: TaskDatabase
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    localdb = TaskDatabase.getDatabase(LocalContext.current)


    Scaffold(modifier = Modifier
        .fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
        ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.CreateTask.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(BottomNavItem.CreateTask.route){
                CreateTaskScreen(localdb = localdb)
            }
            composable(BottomNavItem.ListTask.route){
                ListTaskScreen(localdb = localdb)
            }
            composable(BottomNavItem.Settings.route){
                SettingsScreen(localdb = localdb)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}