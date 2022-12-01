package com.example.smorning

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.smorning.databinding.ActivityQuizBinding

class Quiz : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private var firstChoice = createChoice()
    private var secondChoice = createChoice()
    private var answerSelect= listOf(firstChoice.second, secondChoice.second).random()
    private var count = 3
    lateinit var signal: MediaPlayer
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signal = MediaPlayer.create(this,R.raw.first_alarm_sing)
        signal.isLooping = true
        signal.start()
        binding = ActivityQuizBinding.inflate(layoutInflater)
        binding.chose1.text = firstChoice.first + "   " +firstChoice.second.toString()
        binding.chose2.text = secondChoice.first + "   " +secondChoice.second.toString()
        binding.answer.text = "= $answerSelect"
        binding.counter.text = "Решите $count, чтобы остановить сигнал"
        setContentView(binding.root)
        binding.root.setOnClickListener {
            window.decorView.apply {
                // Hides the navigation bar.
                systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            }
        }
        supportActionBar?.hide()
    }

    @SuppressLint("SetTextI18n")
    fun quiz(){
        firstChoice = createChoice()
        secondChoice = createChoice()
        answerSelect = listOf(firstChoice.second, secondChoice.second).random()
        binding.chose1.text = firstChoice.first + "   " +firstChoice.second.toString()
        binding.chose2.text = secondChoice.first + "   " +secondChoice.second.toString()
        binding.answer.text = "= $answerSelect"
        binding.counter.text = "Решите $count, чтобы остановить сигнал"
    }


    fun finishAllProcess(){
        signal.stop()
        finish()
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
        finishAllProcess()
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
