package com.webpa.mobile.data.security

import android.content.Context
import android.util.Base64
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import javax.inject.Inject

class JwtParser @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun extractUserId(token: String): Long {
        val payload = token.split(".")[1]
        val json = String(
            Base64.decode(payload, Base64.URL_SAFE),
            Charsets.UTF_8
        )

        val obj = JSONObject(json)
        return obj.getLong("id")
    }
}
