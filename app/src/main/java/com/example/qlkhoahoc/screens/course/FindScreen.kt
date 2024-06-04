package com.example.qlkhoahoc.screens

import androidx.compose.ui.graphics.Color
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.qlkhoahoc.screens.course.showCourses

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.qlkhoahoc.methods.findCourse
import com.example.qlkhoahoc.model.Course
import com.example.qlkhoahoc.ui.theme.backgroundColor

@Composable
fun FindScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                backgroundColor
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(backgroundColor),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Find(navController)
        }
    }
}

@Composable
fun Find(navController: NavHostController) {
    var searchTerm by remember { mutableStateOf("") }
    var list by remember { mutableStateOf(mutableListOf<Course>()) }
    var isChecked by remember { mutableStateOf(false) } // State for checkbox

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchTerm,
                onValueChange = { searchTerm = it },
                label = { Text(text = "Tìm kiếm theo tên, mô tả") },

                trailingIcon = {
                    IconButton(
                        onClick = {
                            findCourse(searchTerm, isChecked.toString()) {
                                Log.d("condition ....: ", isChecked.toString())
                                list = it
                                Log.d("Searching:", searchTerm)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = ""
                        )
                    }
                },

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.height(8.dp)) // Add some vertical space between text field and checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },

                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White // Customize checkmark color if needed
                    )
                )
                Spacer(modifier = Modifier.width(8.dp)) // Add some space between checkbox and description
                Text(
                    text = "Các khoá học có ảnh và video",
                    style = MaterialTheme.typography.body1
                )
            }
        }
        showCourses(list = list, navController)
    }
}
//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun FindPreview() {
//    FindScreen()
//}