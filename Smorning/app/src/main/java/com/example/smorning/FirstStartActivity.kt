package com.example.smorning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class FirstStartActivity() : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_start)
        supportActionBar?.hide()
    }
    fun moreInfoActivityCall(view: View){
        startActivity(Intent(this, MoreInfoActivity::class.java))
    }

    fun startApp(view: View){
        startActivity(Intent(this, MainActivity::class.java))
    }
}