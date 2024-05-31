package com.example.qlkhoahoc.screens.course

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.qlkhoahoc.BottomBarScreen
import com.example.qlkhoahoc.methods.auth.TokenManager

import com.example.qlkhoahoc.methods.course.getAllCourses
import com.example.qlkhoahoc.methods.order.getOrderOfUser
import com.example.qlkhoahoc.model.Course
import com.example.qlkhoahoc.ui.theme.*

@Composable
fun AttendedCoursesScreen(navController: NavHostController) {
    val context = LocalContext.current
    val tk: String = "Bearer " + TokenManager.getToken(context).toString()
    var list by remember {
        mutableStateOf(mutableListOf<Course>())
    }

    // Goi ham API va nhan ket qua tra ve thong qua callback
    getOrderOfUser(tk) {
        list = it
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
    ) {}
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Khóa học đã đăng ký",
            modifier = Modifier.padding(8.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,)
        Spacer(modifier = Modifier.height(20.dp))
        showCourses(list = list, navController)
    }
}


//var colorIndex = 0

//@Composable
//fun showCourses(list: MutableList<Course>) {
//    LazyColumn(
//        contentPadding = PaddingValues(all = 6.dp), verticalArrangement = Arrangement.spacedBy(2.dp)
//    ) {
//        items(list) { course ->
//            CourseItem(course = course, courseColors[colorIndex])
//            if (colorIndex >= courseColors.size) {
//                colorIndex = 0
//            } else {
//                colorIndex += 1
//            }
//        }
//    }
//}




