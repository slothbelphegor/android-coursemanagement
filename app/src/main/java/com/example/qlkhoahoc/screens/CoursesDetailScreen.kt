package com.example.qlkhoahoc.screens


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.qlkhoahoc.R
import com.example.qlkhoahoc.model.Course
import com.example.qlkhoahoc.screens.course.EditCourseScreen
import com.example.qlkhoahoc.ui.theme.courseColor1



@Composable
fun CourseDetailScreen(course: Course, backgroundColor: Color, categoryName: String, navController: NavHostController) {
    var showWatchDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Text(text = "Chi tiết khóa học", color = Color.Black)
                },
                backgroundColor = (backgroundColor),
                actions = {
                    IconButton(onClick = {
                        navController.navigate("editCourse/${course.courseId}")
                    }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = Color.Black)
                    }
                }
            )
            Image(
                painter = painterResource(R.drawable.homeicon), // Reference your image resource
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = categoryName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(8.dp))

            course.courseName?.let {
                Text(
                    text = it,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(8.dp))
            course.description?.let {
                Text(
                    text = it,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                course.video?.let { watchVideoButton(context = context, it) }
            }
        }

//        if (showWatchDialog) {
//            Dialog(
//                onDismissRequest = { showWatchDialog = false },
//                properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
//            ) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color.White, shape = RoundedCornerShape(8.dp))
//                        .padding(16.dp)
//                ) {
//                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        Text(
//                            text = "Watch Course",
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(
//                            text = "You are now watching the Kotlin & Jetpack Compose course.",
//                            textAlign = TextAlign.Center
//                        )
//                        Spacer(modifier = Modifier.height(16.dp))
//                        Button(onClick = { showWatchDialog = false }) {
//                            Text(text = "OK")
//                        }
//                    }
//                }
//            }
//        }

        if (showEditDialog) {
            Dialog(
                onDismissRequest = { showEditDialog = false },
                properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Edit Course",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "You are now editing the details of the Kotlin & Jetpack Compose course.",
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { showEditDialog = false }) {
                            Text(text = "OK")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun watchVideoButton(context: Context, videoLink: String) {
    GradientButton(
        text = "Xem khóa học",
        onClick = {
            val url = videoLink
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        },
        gradient = Brush.horizontalGradient(
            colors = listOf(Color(0xFF6200EA), Color(0xFF3700B3))
        ),
    )
}

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    gradient: Brush,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .background(
                gradient,
                shape = RoundedCornerShape(24.dp)
            ) // Updated to 24.dp for rounder corners
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    gradient,
                    shape = RoundedCornerShape(24.dp)
                ) // Updated to 24.dp for rounder corners
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = text, color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseDetailScreen() {
    CourseDetailScreen(Course("0","Python cơ bản",
    "Lập trình Python căn bản","homeicon.png","video",
    0,0), courseColor1,"Lập trình", rememberNavController())
}