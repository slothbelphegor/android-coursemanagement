package com.example.qlkhoahoc.model

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import android.content.Context

data class TokenData(
    val userId: String,
    val username: String,
    val roleId: Int,
)

fun decodeJWT(token: String): TokenData? {
    return try {
        val algorithm = Algorithm.HMAC256("daylabimatgiuachungtanhehihihi")
        val verifier = JWT.require(algorithm).build()
        val jwt: DecodedJWT = verifier.verify(token)

        TokenData(
            userId = jwt.getClaim("userId").asString(),
            username = jwt.getClaim("username").asString(),
            roleId = jwt.getClaim("role_id").asInt()
        )
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
