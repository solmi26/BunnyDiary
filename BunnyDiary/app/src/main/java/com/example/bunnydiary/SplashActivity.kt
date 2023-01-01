package com.example.bunnydiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    //딜레이를 발생시켜 그 이후에 메인 엑티비티로 이동

        Handler(Looper.getMainLooper()).postDelayed({
             val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent) },3000)
    }
}