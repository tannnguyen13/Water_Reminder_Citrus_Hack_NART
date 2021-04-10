package com.example.waterreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        val button = findViewById<Button>(R.id.button_to_weight);
        button.setOnClickListener{
            val intent = Intent(this, grab_weight::class.java);
            startActivity(intent);
        }

        val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        val editor = sharedPref.edit();
    }
}