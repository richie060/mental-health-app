package com.example.mentalhealth.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mentalhealth.Fragment.QusestionsActivity
import kotlinx.android.synthetic.main.activity_welcome_screen.*
import mentalhealth.R

class WelcomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        btnGetStarted.setOnClickListener {
            val intent = Intent(this@WelcomeScreen, QusestionsActivity::class.java)
            startActivity(intent)
        }
    }
}