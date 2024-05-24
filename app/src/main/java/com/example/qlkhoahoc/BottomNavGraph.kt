package com.example.qlkhoahoc

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.qlkhoahoc.screens.AddScreen
import com.example.qlkhoahoc.screens.FindScreen
import com.example.qlkhoahoc.screens.HomeScreen
import com.example.qlkhoahoc.screens.CoursesScreen
import com.example.qlkhoahoc.screens.LoginScreen

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(navController = navController,
        startDestination = BottomBarScreen.Home.route) { //mặc định mở home trước
        composable(route = BottomBarScreen.Home.route) {
            ///gọi màn hình home
            HomeScreen(navController)
        }
        composable(route = BottomBarScreen.Courses.route) {
            CoursesScreen()
        }
        composable(route = BottomBarScreen.AddCourse.route) {
            AddScreen()
        }
        composable(route = BottomBarScreen.Find.route) {
            FindScreen()
        }
        composable("login") { LoginScreen() }
        composable("home") { HomeScreen(navController) }
    }
}