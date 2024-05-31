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
            onChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        FirstPasswordField(
            value = password,
            onChange = { password = it },
            submit = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        PasswordField(
            value = repassword,
            onChange = { repassword = it },
            submit = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            placeholder = "Nhập lại mật khẩu",
            label = "Nhập lại mật khẩu"
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




@Composable
fun FirstPasswordField(
    value: String,
    onChange: (String) -> Unit,
    submit: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Nhập mật khẩu",
    placeholder: String = "Nhập mật khẩu"
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
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun RegisterScreenPreview() {
//
//    RegisterScreen(rememberNavController())
//}

