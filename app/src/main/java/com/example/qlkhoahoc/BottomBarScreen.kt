package com.example.qlkhoahoc

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

// không thể kế thừa từ sealed class
sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector // các icon có sẵn
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Trang chủ",  //tên button hiện ra màn hình
        icon = Icons.Default.Home // icon sử dụng
    )

    object Courses : BottomBarScreen(
        route = "courses",
        title = "Khóa học",  //tên button hiện ra màn hình
        icon = Icons.Default.AccountBox // icon sử dụng
    )

    object AddCourse : BottomBarScreen(
        route = "add",
        title = "Thêm",  //tên button hiện ra màn hình
        icon = Icons.Default.Add // icon sử dụng
    )

    object Find : BottomBarScreen(
        route = "find",
        title = "Find",  //tên button hiện ra màn hình
        icon = Icons.Default.Search // icon sử dụng
    )

    object AttendedCourses : BottomBarScreen(
        route = "attended",
        title = "Đã tham gia",
        icon = Icons.Default.Favorite
    )

    object Additional : BottomBarScreen(
        route = "additional",
        title = "Chức năng mới",
        icon = Icons.Default.Star

    )


}