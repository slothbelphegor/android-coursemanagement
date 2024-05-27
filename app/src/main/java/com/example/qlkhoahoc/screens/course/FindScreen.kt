package com.example.qlkhoahoc.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
    var list by remember {
        mutableStateOf(mutableListOf<Course>())
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchTerm,
            onValueChange = { searchTerm = it },
            label = { Text(text = "Tìm kiếm theo tên, mô tả") },

            trailingIcon = {
                IconButton(
                    onClick = {
                        findCourse(searchTerm) {
                            list = it
                            Log.d("Searching:", searchTerm)
//                            Log.d("Result:", list.toString())
                        }
                    }) {
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
        showCourses(list = list, navController)
        // showCourses(list = courseList, rememberNavController())

    }
}


//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun FindPreview() {
//    FindScreen()
//}