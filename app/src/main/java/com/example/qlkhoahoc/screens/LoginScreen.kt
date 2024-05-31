@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.qlkhoahoc.screens
//package com.whitebatcodes.myloginapplication.interfaces

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
import androidx.compose.material3.*

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
import com.example.qlkhoahoc.model.LoginData
import com.example.qlkhoahoc.ui.theme.backgroundColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var tk: String

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loggedIn by remember { mutableStateOf(false) }
    var isUsernameValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

    fun performLogin(username: String, password: String) {
        val loginData = LoginData(username, password)
        login(context, loginData) { success, token ->
            loggedIn = success
            tk = token
        }
    }

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
            isError = !isUsernameValid
        )
        Spacer(modifier = Modifier.height(30.dp))
        PasswordField(
            value = password,
            onChange = {
                password = it
                isPasswordValid = isValidPassword(password)},
            submit = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            isError = !isPasswordValid
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
                Log.d("LoggedIn before perform",loggedIn.toString())
                if (username == "" || password == "") {
                    Toast.makeText(context,"Bạn chưa nhập đủ thông tin!",Toast.LENGTH_SHORT).show()
                }

                else if (isUsernameValid && isPasswordValid){
                    performLogin(username, password)
                    coroutineScope.launch {
                        delay(1000L) // Đợi 1 giây
                        Log.d("LoggedIn after perform", loggedIn.toString())
                        if (loggedIn) {
                            navController.navigate("home")
                            Toast.makeText(context,"Đăng nhập thành công",Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(context,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else {
                    Toast.makeText(context,"Dữ liệu nhập vào không hợp lệ",Toast.LENGTH_SHORT).show()
                }

            },
            enabled = true,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            Text("Đăng nhập")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navController.navigate("register")
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
fun isValidUsername(username: String): Boolean {
    return username.matches(Regex("[a-zA-Z0-9]+"))
}
@Composable
fun LoginField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Tên tài khoản",
    placeholder: String = "Nhập tên tài khoản",
    isError: Boolean = false
) {

    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        placeholder = { Text(placeholder) },
        label = {
            Text(label)
        },
        singleLine = true,
        isError = isError

    )
}

@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
    submit: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Mật khẩu",
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
            imeAction = ImeAction.Done,
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginPreview() {

    LoginScreen(rememberNavController())
}


