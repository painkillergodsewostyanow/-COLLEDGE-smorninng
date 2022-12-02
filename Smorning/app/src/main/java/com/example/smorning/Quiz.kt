package com.example.smorning

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.smorning.databinding.ActivityQuizBinding

class Quiz : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private var firstChoice = createChoice()
    private var secondChoice = createChoice()
    private var answerSelect = listOf(firstChoice.second, secondChoice.second).random()
    private var count = 5
    lateinit var am: AlarmManager
    lateinit var baseIntent: Intent
    lateinit var pIntent:PendingIntent

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        getSystemService(NotificationManager::class.java).cancel(123)
        quiz()
        am = getSystemService(ALARM_SERVICE) as AlarmManager
        baseIntent = Intent(applicationContext, Alarm::class.java)
        pIntent = PendingIntent.getBroadcast(applicationContext, 1000,
            baseIntent, 0)
        setContentView(binding.root)
        supportActionBar?.hide()
    }


    @SuppressLint("SetTextI18n")
    fun quiz(){
        firstChoice = createChoice()
        secondChoice = createChoice()
        answerSelect = listOf(firstChoice.second, secondChoice.second).random()
        binding.chose1.text = firstChoice.first + "   " + firstChoice.second
        binding.chose2.text = secondChoice.first + "   " + secondChoice.second
        binding.answer.text = "= $answerSelect"
        binding.counter.text = "Решите $count, чтобы остановить сигнал"
    }


    fun finishAllProcess(){
        finish()
        onDestroy()
            am.cancel(pIntent)
    }

    override fun onBackPressed() {

    }
    fun choiceFirst(view: View){
        if (firstChoice.second == answerSelect){
            count --
        }
        else{
            count ++
        }
        if (count == 0){
            finishAllProcess()
        }
        quiz()

    }
    fun choiceSecond(view: View){
        if (secondChoice.second == answerSelect){
            count --
        }
        else{
            count ++
        }
        if (count == 0){
            finishAllProcess()
        }
        quiz()
    }
    fun stopQuiz(view: View){
        finish()
    }
    // создаем рандомный пример и возвращаем его, в виде строки и ответ
    private fun createChoice(): Pair<String, Int> {
        val listOperation = listOf("-","+")
        val x = (0..20).random()
        val y = (0..20).random()
        val z = (0..20).random()
        val q = (0..20).random()
        var x1 = 0
        val operation1 = listOperation.random()
        val operation2 = listOperation.random()
        val operation3 = listOperation.random()
        val str =  x.toString() + operation1 + y.toString() + operation2 + z.toString() +
                operation3 + q.toString()
        if (operation1 == "-"){
            x1 = x - y
        }
        else if (operation1 == "+"){
            x1 = x + y
        }
        if (operation2 == "-"){
            x1 -= z
        }
        else{
            x1 += z
        }
        if (operation3 == "-"){
            x1 -= q
        }
        else{
            x1 +=q
        }
        val answer = x1
        return Pair(str, answer)
    }
}
