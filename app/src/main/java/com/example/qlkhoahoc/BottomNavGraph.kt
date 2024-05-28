package com.example.qlkhoahoc

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.qlkhoahoc.methods.course.getCourseById
import com.example.qlkhoahoc.model.Course
import com.example.qlkhoahoc.screens.*
import com.example.qlkhoahoc.screens.course.AddScreen
import com.example.qlkhoahoc.screens.course.CoursesScreen
import com.example.qlkhoahoc.screens.course.EditCourseScreen
import com.google.gson.Gson

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = BottomBarScreen.Courses.route) {
            CoursesScreen(navController)
        }
        composable(route = BottomBarScreen.AddCourse.route) {
            AddScreen()
        }
        composable(route = BottomBarScreen.Find.route) {
            FindScreen(navController)
        }
        composable("login") { LoginScreen() }
        composable("home") { HomeScreen(navController) }

        composable("editCourse/{courseId}") { backStackEntry ->
            EditCourseScreen(
                navController = navController,
                courseId = backStackEntry.arguments?.getString("courseId")
            )
        }
        composable("courses") { CoursesScreen(navController) }

        composable(
            route = "course_detail/{courseId}/{bgColor}/{categoryName}",
            arguments = listOf(
                navArgument("courseId") { type = NavType.StringType },
                navArgument("bgColor") { type = NavType.LongType },
                navArgument("categoryName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId")

            val bgColorLong = backStackEntry.arguments?.getLong("bgColor")
            val bgColor = bgColorLong?.let { Color(it.toULong()) }
            val categoryName = backStackEntry.arguments?.getString("categoryName")
//
//            if (courseJson != null && bgColorLong != null && categoryName != null) {
//                val course = Gson().fromJson(courseJson, Course::class.java)
//                val bgColor = Color(bgColorLong)
//                CourseDetailScreen(course, bgColor, categoryName)
//            } else {
//                Toast.makeText(LocalContext.current, "Navigation failed", Toast.LENGTH_SHORT).show()
//            }
            if (courseId != null  && bgColor != null && categoryName != null) {
                var course by remember { mutableStateOf<Course?>(null) }
                getCourseById(courseId) { fetchedCourse ->
                    fetchedCourse?.let {
                        course = it
                    }
                }
                Log.d("CourseId",courseId)
                Log.d("Course", course.toString())
                Log.d("Color", bgColor.toString())
                Log.d("Category",categoryName)
                course?.let { CourseDetailScreen(it, bgColor, categoryName) }
            }
        }
    }
}