package com.example.shopper.util

import android.util.Log
import com.example.shopper.BuildConfig

fun logD(
    message: String,
    tag: String = "shoppinglist",
) {
//    if (BuildConfig.DEBUG) {
        Log.d(tag, message)
//    }
}