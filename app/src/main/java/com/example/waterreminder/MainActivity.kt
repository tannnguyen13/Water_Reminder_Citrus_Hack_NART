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
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class name : AppCompatActivity() {

    var count = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)




        createNotificationChannel()
        val button = findViewById<ImageButton>(R.id.b_timer)
        button.setOnClickListener { v: View? ->
            Toast.makeText(this, "Reminder Set!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ReminderBroadcast::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(this, 0, intent, 0)
            val alarmManager =
                getSystemService(ALARM_SERVICE) as AlarmManager
            val timeAtButtonClick = System.currentTimeMillis()
            val tenSecondsInMillis = (1000 * 10).toLong()
            alarmManager[AlarmManager.RTC_WAKEUP, timeAtButtonClick + tenSecondsInMillis] =
                pendingIntent

            val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putInt("Counter_Daily", sharedPref.getInt("Counter_Daily", 0) + 1)
            editor.commit()
            //button.setText(sharedPref.getInt("Counter_Daily", 0))

            val textview = findViewById<TextView>(R.id.streaks)
            textview.setText("Streaks: " + sharedPref.getInt("Counter_Daily", 0).toString())
        }

        val butt2 = findViewById<Button>(R.id.butt2)
        butt2.setOnClickListener { v: View? ->
            Toast.makeText(this, "Reminder Set!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ReminderBroadcast::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(this, 0, intent, 0)
            val alarmManager =
                getSystemService(ALARM_SERVICE) as AlarmManager
            val timeAtButtonClick = System.currentTimeMillis()
            val tenSecondsInMillis = (1000 * 3600).toLong()
            alarmManager[AlarmManager.RTC_WAKEUP, timeAtButtonClick + tenSecondsInMillis] =
                pendingIntent

            val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putInt("Counter_Daily", sharedPref.getInt("Counter_Daily", 0) + 1)
            editor.commit()
            //button.setText(sharedPref.getInt("Counter_Daily", 0))

            val textview = findViewById<TextView>(R.id.streaks)
            textview.setText("Streaks: " + sharedPref.getInt("Counter_Daily", 0).toString())
        }

        val butt = findViewById<Button>(R.id.butt)
        butt.setOnClickListener { v: View? ->
            Toast.makeText(this, "Reminder Set!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ReminderBroadcast::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(this, 0, intent, 0)
            val alarmManager =
                getSystemService(ALARM_SERVICE) as AlarmManager
            val timeAtButtonClick = System.currentTimeMillis()
            val tenSecondsInMillis = (1000 * 1800).toLong()
            alarmManager[AlarmManager.RTC_WAKEUP, timeAtButtonClick + tenSecondsInMillis] =
                pendingIntent

            val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putInt("Counter_Daily", sharedPref.getInt("Counter_Daily", 0) + 1)
            editor.commit()
            //button.setText(sharedPref.getInt("Counter_Daily", 0))

            val textview = findViewById<TextView>(R.id.streaks)
            textview.setText("Streaks: " + sharedPref.getInt("Counter_Daily", 0).toString())
        }

        val variableName = findViewById<ImageButton>(R.id.button_to_weight2);

        variableName.setOnClickListener{
            val intent = Intent(this, grab_weight::class.java);
            startActivity(intent);
        }
//
//        val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
//        val editor = sharedPref.edit();

        val name = findViewById<pl.droidsonroids.gif.GifImageButton>(R.id.button);
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


//    fun onTap(view:View){
//        val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
//
//        val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
//        val editor = sharedPref.edit();
//        editor.putInt("Counter_Daily", sharedPref.getInt("Counter_Daily", 0) + 1);
//        editor.commit();
//
//        val thing = findViewbyId<Textview>
//        attatch
//        thing.setText(sharedPref.getInt)
//
//        //count++
//       // val textview = findViewById<TextView>(R.id.streaks)
//        //textview.text = "Streaks: $count"
//    }

}