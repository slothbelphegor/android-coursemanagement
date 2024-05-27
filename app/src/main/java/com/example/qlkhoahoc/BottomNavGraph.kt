package com.example.qlkhoahoc

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
            route = "course_detail/{course}/{bgColor}/{categoryName}",
            arguments = listOf(
                navArgument("course") { type = NavType.StringType },
                navArgument("bgColor") { type = NavType.LongType },
                navArgument("categoryName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val courseJson = backStackEntry.arguments?.getString("course")
            val bgColorLong = backStackEntry.arguments?.getLong("bgColor")
            val categoryName = backStackEntry.arguments?.getString("categoryName")

            if (courseJson != null && bgColorLong != null && categoryName != null) {
                val course = Gson().fromJson(courseJson, Course::class.java)
                val bgColor = Color(bgColorLong)
                CourseDetailScreen(course, bgColor, categoryName)
            } else {
                Toast.makeText(LocalContext.current, "Navigation failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}