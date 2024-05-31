package com.example.qlkhoahoc.screens.course

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.qlkhoahoc.methods.category.getAllCategories
import com.example.qlkhoahoc.model.Category
import com.example.qlkhoahoc.model.Course
import com.example.qlkhoahoc.ui.theme.backgroundColor
import com.example.qlkhoahoc.methods.addCourse
import com.example.qlkhoahoc.methods.auth.TokenManager
import com.example.qlkhoahoc.model.CourseAdd


@Composable
fun AddScreen() {
    val context = LocalContext.current
    val tk: String = "Bearer " + TokenManager.getToken(context).toString()
    Log.d("Token: ", tk)
    Box(
        modifier = Modifier
            .fillMaxSize()
            // tô màu kiểu gradient trên xuống
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Add(context, tk)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(context: Context, token: String) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    var video by remember { mutableStateOf("") }
    var category_id by remember { mutableStateOf(0) }
    var list by remember {
        mutableStateOf(mutableListOf<Category>())
    }


    // Goi ham API va nhan ket qua tra ve thong qua callback
    getAllCategories {
        list = it
    }

    // biến hứng giá trị true/false mà hàm trả về
    var addResult by remember { mutableStateOf(false) }


    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = name,
        onValueChange = { name = it },
        label = { Text(text = "Tên khóa học (*)") },
        textStyle = TextStyle(color = Color.Black),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = description,
        onValueChange = { description = it },
        label = { Text(text = "Mô tả khóa học (*)") },
        textStyle = TextStyle(color = Color.Black),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Info, contentDescription = "")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        maxLines = 99
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = image,
        onValueChange = { image = it },
        label = { Text(text = "Link hình ảnh") },
        textStyle = TextStyle(color = Color.Black),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Image, contentDescription = "")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = video,
        onValueChange = { video = it },
        label = { Text(text = "Link video") },
        textStyle = TextStyle(color = Color.Black),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.PlayCircleFilled, contentDescription = "")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Chọn thể loại") }
    val options = list
    Box(modifier = Modifier.fillMaxWidth()) {
        TextButton(
            onClick = { expanded = true },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Default.Category, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = selectedOption)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption = option.categoryName.toString()
                        expanded = false
                        category_id = option.categoryId!!
                    },
                    text = { Text(text = option.categoryName.toString()) }
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
        if (name == "" || description == "") {
            Toast.makeText(context, "Hãy nhập đủ các trường bắt buộc",Toast.LENGTH_SHORT).show()
        }
        else {
            val course = CourseAdd(name, description, image, video, category_id)
            Log.d("Course add: ", course.toString())
            addCourse(token, course) { result ->
                if (result) {
                    Toast.makeText(context, "Thêm khóa học thành công!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Thêm khóa học không thành công!", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }) {
        Text(text = "Lưu thông tin")
    }
}


//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun AddPreview() {
//
//    AddScreen()
//}
