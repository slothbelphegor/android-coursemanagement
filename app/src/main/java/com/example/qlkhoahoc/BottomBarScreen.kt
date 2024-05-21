package com.example.qlkhoahoc

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

// không thể kế thừa từ sealed class
sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: ImageVector // các icon có sẵn
        ){
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",  //tên button hiện ra màn hình
        icon = Icons.Default.Home // icon sử dụng
    )
    object Courses: BottomBarScreen(
        route = "courses",
        title = "Courses",  //tên button hiện ra màn hình
        icon = Icons.Default.AccountBox // icon sử dụng
    )
    object AddCourses: BottomBarScreen(
        route = "add",
        title = "Add",  //tên button hiện ra màn hình
        icon = Icons.Default.Add // icon sử dụng
    )
    object Find: BottomBarScreen(
        route = "find",
        title = "Find",  //tên button hiện ra màn hình
        icon = Icons.Default.Search // icon sử dụng
    )

}