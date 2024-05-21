package com.example.qlkhoahoc.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qlkhoahoc.methods.getAllCourses
import com.example.qlkhoahoc.model.Course
import com.example.qlkhoahoc.ui.theme.bg1
import com.example.qlkhoahoc.ui.theme.bg2

@Composable
fun CoursesScreen() {
    var list by remember {
        mutableStateOf(mutableListOf<Course>())
    }
    // Goi ham API va nhan ket qua tra ve thong qua callback
    getAllCourses {
        list = it
    }
    //ve UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        bg1, bg2
                    )
                )
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Courses",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        showCourses(list = list)
    }
}

@Composable
fun showCourses(list: MutableList<Course>) {
    LazyColumn(
        contentPadding = PaddingValues(all = 6.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(list) { course ->
            CourseItem(course = course)
        }
    }
}

@Composable
fun CourseItem(course: Course) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
                .padding(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                course.courseName?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Star, contentDescription = "")
                        Column{
                            Text(
                                modifier = Modifier
                                    .padding(start = 6.dp),
                                text = "ID: ${course.courseId}",
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = 6.dp),
                                text = "Name: ${course.courseName}",
                                color = Color.Blue,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
                course.description?.let {
                    Row {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "")
                        Text(
                            modifier = Modifier
                                .padding(start = 6.dp),
                            text = it,
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
                course.image?.let {
                    Row {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                        Text(
                            modifier = Modifier
                                .padding(start = 6.dp),
                            text = it,
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}
