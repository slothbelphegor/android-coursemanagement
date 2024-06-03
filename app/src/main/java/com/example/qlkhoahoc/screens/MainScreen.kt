package com.example.qlkhoahoc.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.qlkhoahoc.BottomBarScreen
import com.example.qlkhoahoc.BottomNavGraph
import com.example.qlkhoahoc.methods.auth.TokenManager
import com.example.qlkhoahoc.model.decodeJWT

@Composable
fun MainScreen() {
    // nhớ vị trí các màn hình đã di chuyển đến
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {innerPadding ->
        Box(modifier = Modifier.padding(PaddingValues(bottom = innerPadding.calculateBottomPadding()))) {
            BottomNavGraph(navController = navController)
        }
    }
}

// vẽ bottom bar (gồm có 4 icon để gọi 4 màn hình)
@Composable
fun BottomBar(navController: NavHostController) {
    val context = LocalContext.current
    val token = TokenManager.getToken(context).toString()
    val tokenData = decodeJWT(token)
    val roleId = remember { mutableStateOf(0) }

    // nếu token thay đổi thì gán lại roleId
    LaunchedEffect(token) {
        if (tokenData != null) {
            roleId.value = tokenData.roleId
        }
    }
    //danh sách các màn hình
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Courses,
        if (roleId.value == 2) BottomBarScreen.AddCourse else BottomBarScreen.AttendedCourses,
        BottomBarScreen.Find,
        BottomBarScreen.Additional
    )
    // biến nhớ vị trí của màn hình nào
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // hiện tại đang ở màn hình nào (? là not null)
    val currentDestination = navBackStackEntry?.destination
    // vẽ các icon cho bottom bar
    BottomNavigation() {
        screens.filterNotNull().forEach { scr ->
            AddItem(
                screen = scr,
                currentDestination = currentDestination,
                navController = navController
            )
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
