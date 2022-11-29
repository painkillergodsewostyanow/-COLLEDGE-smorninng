package com.example.smorning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MoreInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)
        supportActionBar?.hide()
    }
    fun crossCloseActivity(view: View){
        finish();
    }
}