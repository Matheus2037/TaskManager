package com.example.taskmanager.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanager.Constants
import com.example.taskmanager.SharedPreferences


@Composable
fun CreateTaskScreen(paddingValues: PaddingValues){
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val localData = SharedPreferences(LocalContext.current)
    
    Column(
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
    ) {

        OutlinedTextField(
            value = title,
            onValueChange = {title = it},
            label = { Text("Título")},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = description,
            onValueChange = {description = it},
            label = { Text("Descrição")},
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            contentAlignment = Alignment.Center
        ){

            Button(onClick = {
                localData.save(Constants.TITLE, title)
                localData.save(Constants.DESCRIPTION, description)
                Log.i(
                    "Informacao",
                    "CreateTaskScreen: Titulo: ${localData.get(Constants.TITLE)} Descricao: ${localData.get(Constants.DESCRIPTION)}"
                )
            }) {
                Text("Criar")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CreateTaskScreenPreview(){
    CreateTaskScreen(paddingValues = PaddingValues())
}
