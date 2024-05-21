package com.example.qlkhoahoc.screens

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.qlkhoahoc.BottomBarScreen
import com.example.qlkhoahoc.BottomNavGraph

@Composable
fun MainScreen() {
    // nhớ vị trí các màn hình đã di chuyển đến
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        BottomNavGraph(navController=navController)
    }
}

// vẽ bottom bar (gồm có 4 icon để gọi 4 màn hình)
@Composable
fun BottomBar(navController: NavHostController) {
    //danh sách các màn hình
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Courses,
        BottomBarScreen.AddCourse,
        BottomBarScreen.Find,
    )
    // biến nhớ vị trí của màn hình nào
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // hiện tại đang ở màn hình nào (? là not null)
    val currentDestination = navBackStackEntry?.destination
    // vẽ các icon cho bottom bar
    BottomNavigation() {
        screens.forEach { scr ->
            AddItem(
                screen = scr,
                currentDestination = currentDestination,
                navController = navController)
        }
    }

}

// vẽ từng icon cho bottom bar
@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = { Text(text = screen.title) },
        icon = { Icon(imageVector = screen.icon, contentDescription = "") },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        // màu lúc ko chọn
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        })
}
//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun MainPreview() {
//    MainScreen()
//}
