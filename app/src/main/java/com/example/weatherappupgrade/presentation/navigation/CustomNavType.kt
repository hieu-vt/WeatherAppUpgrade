package com.example.weatherappupgrade.presentation.navigation

import android.net.Uri
import com.google.gson.Gson

data class DetailProfile(val firstName:String, val lastName:String){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}
