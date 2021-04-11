package com.example.waterreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Calander : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calander)

        val clearButt = findViewById<Button>(R.id.clearButton);
        val userGoal = findViewById<EditText>(R.id.editTextTextPersonName);

        clearButt.setOnClickListener {
            userGoal.setText("");
        }

    }

    override fun onPause() {
        super.onPause()

        val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        val editor = sharedPref.edit();

        val goalVal = findViewById<EditText> (R.id.editTextTextPersonName);
        //if (goalVal.text.toString() != "0" && goalVal.text.toString() != "") {
        editor.putString("Goal", goalVal.text.toString());
        editor.commit();
        //}
        //editor.commit();
    }

    override fun onResume() {
        super.onResume()

        val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        val editor = sharedPref.edit();

        val goalVal = findViewById<EditText> (R.id.editTextTextPersonName);
        if (sharedPref.getString("Goal", "" ) != "") {
            goalVal.setText(sharedPref.getString("Goal", ""));
        }
        else {
            goalVal.setText("");
        }

    }
}