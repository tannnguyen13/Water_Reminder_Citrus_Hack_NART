package com.example.waterreminder

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil


class name : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        createNotificationChannel()
        val button = findViewById<Button>(R.id.b_timer)
        button.setOnClickListener { v: View? ->
            Toast.makeText(this, "reminder Set!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ReminderBroadcast::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(this, 0, intent, 0)
            val alarmManager =
                getSystemService(ALARM_SERVICE) as AlarmManager
            val timeAtButtonClick = System.currentTimeMillis()
            val tenSecondsInMillis = (1000 * 10).toLong()
            alarmManager[AlarmManager.RTC_WAKEUP, timeAtButtonClick + tenSecondsInMillis] =
                pendingIntent
        }

        val variableName = findViewById<Button>(R.id.button_to_weight2);
        variableName.setOnClickListener{
            val intent = Intent(this, grab_weight::class.java);
            startActivity(intent);
        }
//
        val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        val editor = sharedPref.edit();

        val name = findViewById<Button>(R.id.button3);
        name.setOnClickListener{
            val intent = Intent(this, Calander::class.java);
            startActivity(intent);
        }
        Log.d("CREATION", "jjj");
        val drink_button = findViewById<Button>(R.id.drink_button);
        drink_button.setOnClickListener {
            if (sharedPref.getInt("Water_Required",0) > 0) {
                Log.d("CREATION", "jjjjjjjj");
                editor.putInt("Water_Required", sharedPref.getInt("Water_Required", 0) - 8);
                editor.commit();
                Log.d("CREATION", sharedPref.getInt("Water_Required", 0).toString());
                if (sharedPref.getInt("Water_Required", 0) < 0) {
                    editor.putInt("Water_Required", 0);
                    editor.commit();
                }
                Log.d("CREATION", sharedPref.getInt("Water_Required", 0).toString());

                val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
                val editor = sharedPref.edit();
                val printingVal = findViewById<TextView>(R.id.printingLitersLeft);
                val likeWater = findViewById<TextView>(R.id.amount_of_cups)
                if (sharedPref.getInt("Water_Required", 0) >= 0) {
                    printingVal.setText(
                        sharedPref.getInt("Water_Required", 0).toString() + " Oz left for today"
                    );
                    var value = sharedPref.getInt("Water_Required", 0) / 8.0;
                    likeWater.setText(ceil(value).toString());
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        val editor = sharedPref.edit();
        val printingVal = findViewById<TextView>(R.id.printingLitersLeft);

        if (sharedPref.getInt("Water_Required",0) != 0){
            printingVal.setText(sharedPref.getInt("Water_Required",0).toString() + " Oz left for to day");
            val likeWater = findViewById<TextView>(R.id.amount_of_cups)

            var value = sharedPref.getInt("Water_Required", 0) / 8.0;
            likeWater.setText(ceil(value).toString());
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "remindachanel"
            val description = "Channel for reminda"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("notifyLemubit", name, importance)
            channel.description = description
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}