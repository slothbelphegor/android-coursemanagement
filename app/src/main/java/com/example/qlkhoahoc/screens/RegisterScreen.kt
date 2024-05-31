package com.example.qlkhoahoc.screens


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.qlkhoahoc.methods.auth.login
import com.example.qlkhoahoc.methods.auth.register
import com.example.qlkhoahoc.model.LoginData
import com.example.qlkhoahoc.model.RegisterData
import com.example.qlkhoahoc.ui.theme.backgroundColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(navController: NavHostController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var tk: String

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repassword by remember { mutableStateOf("") }
    var isUsernameValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }
    var isRePasswordValid by remember { mutableStateOf(true) }
    var registered by remember { mutableStateOf(false) }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        LoginField(
            value = username,
            onChange = {
                username = it
                isUsernameValid = isValidUsername(username)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            label = "Tên tài khoản",
            placeholder = "(chỉ gồm chữ và số)",
            isError = !isUsernameValid
        )
        Spacer(modifier = Modifier.height(30.dp))
        FirstPasswordField(
            value = password,
            onChange = {
                password = it
                isPasswordValid = isValidPassword(password)
                isRePasswordValid = (password == repassword)},
            submit = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            label = "Mật khẩu",
            placeholder = "(ít nhất 8 ký tự, gồm chữ hoa, chữ thường, số và ký tự đặc biệt)",
            isError = !isPasswordValid
        )
        Spacer(modifier = Modifier.height(30.dp))
        PasswordField(
            value = repassword,
            onChange = {
                repassword = it
                isRePasswordValid = (password == repassword)},
            submit = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            placeholder = "Nhập lại mật khẩu",
            label = "Nhập lại mật khẩu",
            isError = !isRePasswordValid
        )
        Spacer(modifier = Modifier.height(20.dp))
//        Row(modifier = Modifier
//            .align(Alignment.Start)
//            .padding(horizontal = 25.dp)) {
//            LabeledCheckbox(
//                label = "Ghi nhớ đăng nhập",
//                onCheckChanged = {
//                },
//                isChecked = false
//            )
//        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (isUsernameValid && isPasswordValid && isRePasswordValid) {
                    val registerData = RegisterData(username, password, repassword)
                    // hàm đăng ký ở đây
                    register(registerData) { success ->
                        registered = success
                    }

                    coroutineScope.launch {
                        delay(1000L) // Đợi 1 giây

                        if (registered) {
                            navController.navigate("login")
                            Toast.makeText(context, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(context, "Đăng ký tài khoản thất bại", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                else if (username == "" || password == "" || repassword == "") {
                    Toast.makeText(context,"Chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(context,"Dữ liệu nhập không hợp lệ",Toast.LENGTH_SHORT).show()
                }

            },
            enabled = true,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            Text("Đăng ký")
        }

    }
}

fun isValidPassword(password: String): Boolean {
    return password.matches(Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$"))
}


@Composable
fun FirstPasswordField(
    value: String,
    onChange: (String) -> Unit,
    submit: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Nhập mật khẩu",
    placeholder: String = "Nhập mật khẩu",
    isError: Boolean = false
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Key,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
                if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }


    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = { submit() }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        isError = isError
    )
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun RegisterScreenPreview() {
//
//    RegisterScreen(rememberNavController())
//}

