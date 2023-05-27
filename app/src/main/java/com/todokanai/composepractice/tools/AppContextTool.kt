package com.todokanai.composepractice.tools

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.todokanai.composepractice.application.MyApplication.Companion.appContext

/**
 * Application Context 사용하는 method 중 자주 쓸거같은거 모음
 */

class AppContextTool {
    val context = appContext

    fun makeShortToast(message: String) {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            Toast.makeText(context,message, Toast.LENGTH_SHORT).show() },0)
    }       // Coroutine 내부에서 Toast를 띄우기
}