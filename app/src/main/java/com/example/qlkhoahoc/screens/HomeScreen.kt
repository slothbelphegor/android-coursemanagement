package com.example.qlkhoahoc.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.layout.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight


import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.qlkhoahoc.R
import com.example.qlkhoahoc.ui.theme.backgroundColor

val members1: List<String> = listOf(
    "Trần Đình An",
    "Nguyễn Tiến Đạt"
)
val members2: List<String> = listOf(
    "Vũ Thị Diệu Anh",
    "Lê Nguyễn Trung Mẫn"
)
const val strongestMember = "Nguyễn Quang Trung Nhân"

@Composable
fun AuthorRow(value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(6.dp),
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.TopCenter // trên xuống, căn giữa

    ) {
        Column {
            DetailSection()
            ImageSection()
            AuthorSection()
            Button( onClick = { navController.navigate("login") },
                modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Đăng nhập/Đăng ký",
                    style = TextStyle(fontSize = 14.sp)
                )
            }
        }
    }
}

@Composable
fun AuthorSection() {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = "Thành viên nhóm:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Red
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    members1.forEachIndexed { _, member ->
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AuthorRow(value = member)
                        }
                    }
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    members2.forEachIndexed { _, member ->
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AuthorRow(value = member)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                AuthorRow(value = strongestMember)
            }
        }
    }
}


@Composable
fun DetailSection() {
//    Card(
//        modifier = Modifier
//            .padding(10.dp)
//            .fillMaxWidth()
//            .background(Color(204, 207, 220)),
//        elevation = CardDefaults.cardElevation(10.dp),
//
//    ) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.app_title),
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Dành cho giảng viên",
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(79, 104, 196),
                fontStyle = FontStyle.Italic
            )
        }
    }
}
//}

@Composable
fun ImageSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(350.dp)
            //.clip(CircleShape)
            //.border(5.dp, Color.Red, CircleShape)
            , painter = painterResource(id = R.drawable.homeicon),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomePreview() {

    HomeScreen(rememberNavController())
}