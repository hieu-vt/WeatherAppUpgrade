package com.example.weatherappupgrade.presentation.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson

abstract class JsonNavType<T> : NavType<T>(isNullableAllowed = false) {
    abstract fun fromJsonParse(value: String): T
    abstract fun T.getJsonParse(): String
    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let { parseValue(it) }
    override fun parseValue(value: String): T = fromJsonParse(value)
    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, value.getJsonParse())
    }
}


data class DetailProfile(val firstName:String, val lastName:String){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

class DetailProfileArgType : JsonNavType<DetailProfile>() {
    override fun fromJsonParse(value: String): DetailProfile = Gson().fromJson(value, DetailProfile::class.java)

    override fun DetailProfile.getJsonParse(): String = Gson().toJson(this)
}
