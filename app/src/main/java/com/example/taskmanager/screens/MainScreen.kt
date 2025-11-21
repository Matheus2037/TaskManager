package com.example.taskmanager.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.screens.navigation.BottomNavItem
import com.example.taskmanager.screens.navigation.BottomNavigationBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()

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
                CreateTaskScreen()
            }
            composable(BottomNavItem.ListTask.route){
                ListTaskScreen()
            }
            composable(BottomNavItem.Settings.route){
                SettingsScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}