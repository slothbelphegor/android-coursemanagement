package com.example.qlkhoahoc.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*



import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qlkhoahoc.methods.getAllCourses
import com.example.qlkhoahoc.model.Course
import com.example.qlkhoahoc.ui.theme.*



@Composable
fun CoursesScreen() {
    var list by remember {
        mutableStateOf(mutableListOf<Course>())
    }
    // Goi ham API va nhan ket qua tra ve thong qua callback
    getAllCourses {
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
            ),
        contentAlignment = Alignment.TopCenter
    ) {
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        showCourses(list = list)
    }
}
val courseColors = listOf( courseColor1, courseColor2, courseColor3)
var colorIndex = 0
@Composable
fun showCourses(list: MutableList<Course>) {
    LazyColumn(
        contentPadding = PaddingValues(all = 6.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(list) { course ->
            CourseItem(course = course, courseColors[colorIndex])
            if (colorIndex >= courseColors.size) {
                colorIndex = 0
            }
            else {
                colorIndex += 1
            }
        }
    }
}

@Composable
fun CourseItem(course: Course, bgColor: Color) {

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(bgColor)
                .padding(12.dp)
        ) {
            Row {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(bgColor)
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                    ) {
                        Column (modifier = Modifier.weight(2f)){
                            course.categoryId?.let {
                                Text(
                                    modifier = Modifier
                                        .padding(1.dp),
                                    text =  "${course.categoryId}",
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                            course.courseName?.let {
                                Text(
                                    modifier = Modifier
                                        .padding(10.dp),
                                    text =  "${course.courseName}",
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(4.dp)) // Tạo khoảng cách giữa Text và Icon
                        Box(

                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "")
                        }
                    }


                    course.description?.let {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "")
                            Text(
                                modifier = Modifier
                                    .padding(start = 6.dp),
                                text = it,
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CoursesPreview() {
    CourseItem(Course("2",
        "Lập trình Python căn bảnnnnnnnnnnnnnnnnnnnnnnn nnnnnnnnnnnnnnn",
        "Với vỏn vẹn 1 video thời lượng 12 tiếng, Bro Code đã cung cấp đầy đủ những" +
                " kiến thức căn bản nhất về Python. Khóa học này được nhiều người đón nhận nhờ" +
                " tính dễ tiếp cận và gần gũi của nó. ",
    "image","Video",2,1), courseColor1)

}
