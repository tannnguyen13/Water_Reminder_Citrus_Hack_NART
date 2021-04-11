package com.example.waterreminder

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



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
//        val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
//        val editor = sharedPref.edit();

        val name = findViewById<Button>(R.id.button3);
        name.setOnClickListener{
            val intent = Intent(this, Calander::class.java);
            startActivity(intent);
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