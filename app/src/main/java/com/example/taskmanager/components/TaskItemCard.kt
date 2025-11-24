package com.example.taskmanager.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.Constants

@Composable
fun TaskItemCard(modifier: Modifier = Modifier){
    val localData = com.example.taskmanager.SharedPreferences(LocalContext.current)
    var showMenu by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }

    ElevatedCard(elevation = CardDefaults.cardElevation(
        defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(142.dp)
        .padding(10.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()){
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = localData.get(Constants.TITLE)?: "", fontWeight = FontWeight.SemiBold, fontSize = 16.sp,
                    modifier = Modifier.weight(0.85f)
                )
                IconButton(
                    onClick = { showMenu = true },
                    modifier = Modifier.weight(0.15f).size(24.dp)
                ) {
                    Icon(
                        Icons.Rounded.MoreVert,
                        contentDescription = "Task Options"
                    )
                }
            }
            Row(modifier = Modifier
                .padding(top = 8.dp)
            ) {
                Text(text =  localData.get(Constants.DESCRIPTION)?: "",
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    overflow = TextOverflow.Ellipsis)
            }
        }
    }
    if (showMenu) {
        Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd).padding(8.dp)){
            TaskDropdownMenu(
                onDismiss = { showMenu = false },
                onEditClick = {
                    showBottomSheet = true
                },
                onDeleteClick = {
                    /* TODO */
                }
            )
        }
    }
    if (showBottomSheet) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TaskPartialBottomSheet(onDismiss = { showBottomSheet = false })
        }
    }
}

@Composable
@Preview
fun TaskItemCardPreview(){
    TaskItemCard()
}