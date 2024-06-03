package com.example.qlkhoahoc.screens.course


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.qlkhoahoc.methods.category.getAllCategories
import com.example.qlkhoahoc.methods.course.sortCourses
import com.example.qlkhoahoc.model.Course
import com.example.qlkhoahoc.ui.theme.*


@Composable
fun CoursesScreen(navController: NavHostController) {
    var list by remember {
        mutableStateOf(mutableListOf<Course>())
    }
    var sortAsc by remember { mutableStateOf(false) }
    val sortText = if (!sortAsc) "Sắp xếp theo độ phổ biến giảm dần" else "Sắp xếp theo độ phổ biến tăng dần"

    // Goi ham API va nhan ket qua tra ve thong qua callback
    // getAllCourses {
    //     list = it
    // }
    sortCourses(if (sortAsc) "asc" else "desc") { sortedList ->
        list = sortedList
    }
    //ve UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        backgroundColor, bg2
                    )
                )
            ), contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = sortText, fontSize = 14.sp, fontWeight = FontWeight.Normal)
                    IconButton(
                        onClick = {
                            sortAsc = !sortAsc
                            sortCourses(if (sortAsc) "asc" else "desc") { sortedList ->
                                list = sortedList
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.Sort, contentDescription = "Sort")
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            showCourses(list = list, navController)
        }
    }
}

val courseColors = listOf(courseColor1, courseColor2, courseColor3)

@Composable
fun showCourses(list: MutableList<Course>, navController: NavHostController) {
    var colorIndex = 0
    var categoryMap by remember {
        mutableStateOf(mapOf<Int, String>())
    }

    getAllCategories { categories ->
        categoryMap = categories.associateBy({ it.categoryId!! }, { it.categoryName!! })
    }

    LazyColumn(
        contentPadding = PaddingValues(all = 6.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(list) { course ->
            CourseItem(
                course = course,
                courseColors[colorIndex],
                categoryMap,
                navController,
                onClick = { })
            colorIndex = (colorIndex + 1) % courseColors.size
        }
    }
}

@Composable
fun CourseItem(
    course: Course,
    bgColor: Color,
    categoryMap: Map<Int, String>,
    navController: NavHostController,
    onClick: () -> Unit
) {
    val categoryName = categoryMap[course.categoryId] ?: "Unknown Category"

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(bgColor)
                .padding(12.dp)
        ) {
            Row {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(bgColor)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.weight(2f)) {
                            course.categoryId?.let {
                                Text(
                                    modifier = Modifier.padding(1.dp),
                                    text = categoryName,
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                            course.courseName?.let {
                                Text(
                                    modifier = Modifier.padding(10.dp),
                                    text = "${course.courseName}",
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(4.dp)) // Tạo khoảng cách giữa Text và Icon

                        Box(

                            contentAlignment = Alignment.CenterEnd
                        ) {
                            IconButton(onClick = {
                                val bgColorLong = bgColor.value.toLong()
                                navController.navigate("course_detail/${course.courseId}/$bgColorLong/$categoryName")
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu, contentDescription = ""
                                )
                            }

                        }
                    }
                    course.description?.let {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Info, contentDescription = ""
                            )
                            Text(
                                modifier = Modifier.padding(start = 6.dp),
                                text = it,
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    course.orderCount?.let {
                        Text(
                            modifier = Modifier.padding(top = 6.dp),
                            text = "Số lượng khóa học được đăng ký: $it",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }

                }
            }
        }
    }
}



