package com.example.marvelstaff.util

import android.util.Log
import com.example.marvelstaff.BuildConfig

class Logger {
    companion object {
        fun log(tag: String, msg: String) {
            if (BuildConfig.DEBUG) {
                Log.v(tag, msg)
            }
        }

        fun log(tag: String, msg: String, tr: Throwable) {
            if (BuildConfig.DEBUG) {
                Log.v(tag, msg, tr)
            }
        }
    }
}