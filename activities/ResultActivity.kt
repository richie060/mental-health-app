package com.example.mentalhealth.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mentalhealth.MainActivityM
import com.example.mentalhealth.constants.Constants
import kotlinx.android.synthetic.main.activity_result.*
import mentalhealth.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val username = intent.getStringExtra(Constants.USER_NAME)
        tv_name.text = username

        val totalQuestion = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)
//        We recommend you to start your self medication now
        if (correctAnswers < 3){
            tv_score.text = "Your Mental health  score is $correctAnswers out of $totalQuestion which is Optimal!! We recommend you  start  self medication now"
        }else if (correctAnswers >3 && correctAnswers < 5 ){
            tv_score.text = "Your Mental health  score is $correctAnswers out of $totalQuestion which is Average!! We recommend you  start  self medication now"
        }else{
            tv_score.text = "Your Mental health  score is $correctAnswers out of $totalQuestion which is Above Average!! We recommend you  start  self improvement now"
        }

        btn_finish.setOnClickListener {
            startActivity(Intent(this, MainActivityM::class.java))
            finish()
        }
    }
}