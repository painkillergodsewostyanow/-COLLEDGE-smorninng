package com.example.smorning

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Alarm: BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {
        val song = R.raw.first_alarm_sing
        val signal = MediaPlayer.create(context, song)
        val intent = Intent(context, Quiz::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pIntent = PendingIntent.getActivity(context, 123, intent, 0)
        val builder = NotificationCompat.Builder(context!!,"channel")
            .setSmallIcon(R.mipmap._logo_round)
            .setContentTitle("Пора вставать")
            .setContentText("Решите пару задач что бы проснуться!")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pIntent)
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123, builder.build())
        if (signal != null) {
            signal.setVolume(100f, 100f)
            signal.start()
            signal.setOnCompletionListener { signal -> signal.release() }
        }
    }
}