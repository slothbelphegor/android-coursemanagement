package com.example.qlkhoahoc.screens.course

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.qlkhoahoc.methods.auth.TokenManager
import com.example.qlkhoahoc.methods.course.editCourse
import com.example.qlkhoahoc.methods.course.getCourseById
import com.example.qlkhoahoc.methods.category.getAllCategories
import com.example.qlkhoahoc.model.Category
import com.example.qlkhoahoc.model.Course
import com.example.qlkhoahoc.model.CourseAdd
import com.example.qlkhoahoc.ui.theme.backgroundColor
import com.example.qlkhoahoc.ui.theme.bg2

@Composable
fun EditCourseScreen(navController: NavHostController, courseId: String?) {
    val context = LocalContext.current
    val tk: String = "Bearer " + TokenManager.getToken(context).toString()
//    Log.d("Token: ", tk)
    var course by remember { mutableStateOf<Course?>(null) }
    var courseName by remember { mutableStateOf("") }
    var courseDescription by remember { mutableStateOf("") }
    var courseImage by remember { mutableStateOf("") }
    var courseVideo by remember { mutableStateOf("") }
    var categoryId by remember { mutableStateOf(0) }
    var dataLoaded by remember { mutableStateOf(false) }
    var list by remember {
        mutableStateOf(mutableListOf<Category>())
    }
    var selectedOption by remember { mutableStateOf("Chọn thể loại") }
    // Goi ham API va nhan ket qua tra ve thong qua callback
    getAllCategories {
        list = it
    }

    LaunchedEffect(Unit) {
        getAllCategories { fetchedCategories ->
            list = fetchedCategories

            if (categoryId != 0) {
                list.find { it.categoryId == categoryId }?.let { category ->
                    selectedOption = category.categoryName ?: "Chọn thể loại"
                }
            }
        }
    }

    if (courseId != null && !dataLoaded) {
        getCourseById(courseId) { fetchedCourse ->
            fetchedCourse?.let {
                course = it
                courseName = it.courseName ?: ""
                courseDescription = it.description ?: ""
                courseImage = it.image ?: ""
                courseVideo = it.video ?: ""
                categoryId = it.categoryId ?: 0
                dataLoaded = true
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
                var expanded by remember { mutableStateOf(false) }
                val options = list
                Box(modifier = Modifier.fillMaxWidth()) {
                    TextButton(
                        onClick = { expanded = true },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Icon(imageVector = Icons.Default.Category, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = selectedOption)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedOption = option.categoryName.toString()
                                    expanded = false
                                    categoryId = option.categoryId!!
                                },
                                text = { Text(text = option.categoryName.toString()) }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    // Create the updated course object
                    val updatedCourse = CourseAdd(
                        course = courseName,
                        description = courseDescription,
                        image = courseImage,
                        video = courseVideo,
                        category_id = categoryId,
                    )

                    // Call editCourse
                    editCourse(tk, courseId!!, updatedCourse) { success ->
                        if (success == true) {
                            Log.d("EditCourseScreen", "Course updated successfully")
                            Toast.makeText(context, "Course updated successfully", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Log.e("EditCourseScreen", "Failed to update course")
                            Toast.makeText(context, "Failed to update course", Toast.LENGTH_SHORT).show()
                        }
                    }
                    Log.d("EditCourseScreen", "Successfully updated course: $updatedCourse")
                    Toast.makeText(context,"Course updated successfully",Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }) {
                    Text(text = "Save")
                }
            } ?: Text(text = "Loading...")
        }
    }
}
