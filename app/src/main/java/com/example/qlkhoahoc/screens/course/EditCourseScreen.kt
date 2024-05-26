package com.example.qlkhoahoc.screens.course

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.qlkhoahoc.methods.course.editCourse
import com.example.qlkhoahoc.methods.course.getCourseById
import com.example.qlkhoahoc.model.Course
import com.example.qlkhoahoc.ui.theme.backgroundColor
import com.example.qlkhoahoc.ui.theme.bg2

@Composable
fun EditCourseScreen(navController: NavHostController, courseId: String?) {
    val context = LocalContext.current
    var course by remember { mutableStateOf<Course?>(null) }
    var courseName by remember { mutableStateOf("") }
    var courseDescription by remember { mutableStateOf("") }
    var courseImage by remember { mutableStateOf("") }
    var courseVideo by remember { mutableStateOf("") }
    var categoryId by remember { mutableStateOf(0) }

    if (courseId != null) {
        getCourseById(courseId) { fetchedCourse ->
            fetchedCourse?.let {
                course = it
                courseName = it.courseName ?: ""
                courseDescription = it.description ?: ""
                courseImage = it.image ?: ""
                courseVideo = it.video ?: ""
                categoryId = it.categoryId ?: 0
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(backgroundColor, bg2))),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Edit Course", color = Color.Black)
            Spacer(modifier = Modifier.height(20.dp))
            course?.let {
                TextField(
                    value = courseName,
                    onValueChange = { courseName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.White),
                    label = { Text("Course Name") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = courseDescription,
                    onValueChange = { courseDescription = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.White),
                    label = { Text("Course Description") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = courseImage,
                    onValueChange = { courseImage = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.White),
                    label = { Text("Course Image URL") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = courseVideo,
                    onValueChange = { courseVideo = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.White),
                    label = { Text("Course Video URL") }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    // Create the updated course object
                    val updatedCourse = Course(
                        courseId = it.courseId,
                        courseName = courseName,
                        description = courseDescription,
                        image = courseImage,
                        video = courseVideo,
                        categoryId = categoryId,
                        categoryName = ""
                    )

                    // Call editCourse
                    editCourse(context, courseId!!, updatedCourse) { updatedCourse ->
                        if (updatedCourse != null) {
                            Log.d("EditCourseScreen", "Successfully updated course: $updatedCourse")
                            navController.popBackStack()
                        } else {
                            Log.e("EditCourseScreen", "Failed to update course")
                        }
                    }
                }) {
                    Text(text = "Save")
                }
            } ?: Text(text = "Loading...")
        }
    }

}