package com.example.qlkhoahoc.screens


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.qlkhoahoc.R
import com.example.qlkhoahoc.methods.auth.TokenManager
import com.example.qlkhoahoc.model.Course
import com.example.qlkhoahoc.model.decodeJWT
import com.example.qlkhoahoc.ui.theme.courseColor1


@Composable
fun getApiUrl(context: Context): String {
    val apiUrl = context.getString(R.string.URL_API)
    val port = context.getString(R.string.PORT)
    return "$apiUrl:$port/uploads/"
}

@Composable
fun CourseDetailScreen(
    course: Course,
    backgroundColor: Color,
    categoryName: String,
    navController: NavHostController
) {
    var showWatchDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val URL_IMAGE = getApiUrl(context)

    val token = TokenManager.getToken(context).toString()
    val tokenData = decodeJWT(token)
    val roleId = remember { mutableStateOf(0) }
    // nếu token thay đổi thì gán lại roleId
    LaunchedEffect(token) {
        if (tokenData != null) {
            roleId.value = tokenData.roleId
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Text(text = "Chi tiết khóa học", color = Color.Black)
                },
                backgroundColor = (backgroundColor),
                actions = {
                    IconButton(onClick = {
                        if (roleId.value == 2) {
                            navController.navigate("editCourse/${course.courseId}")
                        }
                        else {
                            Toast.makeText(context,"Chức năng không khả dụng",Toast.LENGTH_SHORT).show()
                        }

                    }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit",
                            tint = Color.Black
                        )
                    }
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                course.image?.let {
                    val p = rememberImagePainter(
                        data = URL_IMAGE + it,
                        builder = {
                            placeholder(R.drawable.ic_launcher_background)
                            error(R.drawable.noimage)
                            crossfade(1000) // thời gian hiển thị từ placeholder sang hình
                            transformations( // gọt tròn các góc hình
                                RoundedCornersTransformation(50f)
                            )
                            size(2000, 1000)
                        })
                    Image(
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth(),
                        painter = p,
                        contentDescription = ""
                    )

                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = categoryName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
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
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            course.description?.let {
                Text(
                    text = it,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            // nếu là khách hàng đã đăng ký (thay cho true) hoặc là admin và moderator
            if ((roleId.value == 3 && true) || roleId.value == 1 || roleId.value == 2) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    course.video?.let { watchVideoButton(context = context, it, roleId.value) }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            GradientButton(
                modifier = Modifier.padding(16.dp),
                text = "Đăng ký khóa học",
                onClick = {
                    if (roleId.value == 3) { // nếu đã đăng nhập
                        Toast.makeText(context,"Đăng kí khóa học thành công!",Toast.LENGTH_SHORT).show()
                        // thêm vào danh sách khóa học đã đăng ký
                    }
                    // nên có thêm trường hợp "hủy đăng ký nếu đã đăng ký"
                    else if (roleId.value == 0){ // chưa đăng nhập
                        Toast.makeText(context,"Bạn chưa đăng nhập!",Toast.LENGTH_SHORT).show()
                    }
                },
                gradient = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF6200EA), Color(0xFF3700B3))
                ),
            )
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
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                )
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
fun watchVideoButton(context: Context, videoLink: String, roleId: Int) {
    GradientButton(
        text = "Xem khóa học",
        onClick = {
            if ((roleId == 3) || roleId == 1 || roleId == 2) { // nếu đã có tài khoản và đã đăng ký
                val url = videoLink
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
                catch(ex: Exception) {
                    Toast.makeText(context,"Có lỗi xảy ra. Xin hãy thử lại sau hoặc liên hệ hỗ trợ.",Toast.LENGTH_SHORT).show()
                }

            }
            else {
                Toast.makeText(context,"Bạn chưa đăng kí khóa học này",Toast.LENGTH_SHORT).show()
            }
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
    CourseDetailScreen(
        Course(
            "0", "Python cơ bản",
            "Lập trình Python căn bản", "homeicon.png", "video",
            0, 0
        ), courseColor1, "Lập trình", rememberNavController()
    )
}