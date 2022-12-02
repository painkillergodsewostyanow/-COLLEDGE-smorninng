package com.example.smorning

import android.app.*
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import kotlinx.coroutines.NonCancellable.cancel
import java.util.*

class CreateAlarm : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {
    lateinit var notificationManager: NotificationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_alarm)
        createNotificationChannel()
        supportActionBar?.hide()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Пора вставать"
            val description = "Решите небольшой пару что бы проснуться!"
            val concern = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("channel", name, concern)
            channel.description = description
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

    }

    override fun onTimeSet(p0: TimePicker?, hours: Int, minutes: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hours)
        calendar.set(Calendar.MINUTE, minutes)
        val am = getSystemService(ALARM_SERVICE) as AlarmManager
        val baseIntent = Intent(applicationContext, Alarm::class.java)
        val pIntent = PendingIntent.getBroadcast(applicationContext, 1000,
            baseIntent, 0
        )
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, 60000, pIntent)
    }

    fun setting(view: View){
        val timeSet = GetTime()
        timeSet.show(supportFragmentManager, "time_set")
    }
    fun stopSignal(view: View){
        startActivity(Intent(this, Quiz::class.java))
    }
}