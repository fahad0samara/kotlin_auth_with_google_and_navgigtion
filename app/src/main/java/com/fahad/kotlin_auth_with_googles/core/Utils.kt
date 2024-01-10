package com.fahad.kotlin_auth_with_googles.core

import android.util.Log
import com.fahad.kotlin_auth_with_googles.core.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())
    }
}