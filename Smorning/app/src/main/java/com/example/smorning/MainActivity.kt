package com.example.smorning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }
    fun startTestQuiz(view: View){
        startActivity(Intent(this, Quiz::class.java))
    }
    fun createAlarm(view: View){
        startActivity(Intent(this, CreateAlarm::class.java))
    }
}