package com.example.qlkhoahoc.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class GroupInfo(
    val groupName: String,
    val appName: String,
    val members: List<String>
)

@Composable
fun GroupCard(groupInfo: GroupInfo) {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            FieldWithIcon(Icons.Default.AccountBox,  groupInfo.groupName)
            FieldWithIcon(Icons.Default.Person,groupInfo.appName)
            groupInfo.members.forEachIndexed { index, member ->
                FieldWithIcon(Icons.Default.Person, member)
            }
        }
    }
}

@Composable
fun FieldWithIcon(icon: ImageVector, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = "")
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = value)
    }
}

@Composable
fun tempscreen()  {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.TopCenter // trên xuống, căn giữa

    ) {
        val groupInfo = GroupInfo(
            groupName = "Jetpack Compose Group",
            appName = "ComposeApp",
            members = listOf("Member 1", "Member 2", "Member 3", "Member 4", "Member 5")
        )

        GroupCard(groupInfo = groupInfo)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun temppreview() {
    tempscreen()
}