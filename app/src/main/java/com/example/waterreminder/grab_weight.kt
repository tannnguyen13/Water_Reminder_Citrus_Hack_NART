package com.example.waterreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.content.SharedPreferences
import android.util.Log
import android.widget.TextView
import androidx.core.text.set
import kotlin.math.ceil


class grab_weight : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grab_weight)

        val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        val editor = sharedPref.edit();

        val weight = resources.getStringArray(R.array.weight_identifiers);
        val spinner = findViewById<Spinner>(R.id.weight_spinner);
        if (spinner != null){
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, weight);
            spinner.adapter = adapter;
        }

        val weightVal = findViewById<EditText> (R.id.editText_Weight);
        if (sharedPref.getInt("Weight", 0) != 0){
            weightVal.setText(sharedPref.getInt("Weight", 0).toString());
            //weightVal.setText((sharedPref.getInt("Weight", 0)).toString());
        }
        if (sharedPref.getString("ScaleType", "") == "LBS"){
            spinner.setSelection(0);
        }
        else{
            spinner.setSelection(1);
        }
        Log.d("CREATION", "here");
    }
    override fun onUserInteraction(){
        super.onUserInteraction();
        Log.d("CREATION", "bread");
        val rec_water = findViewById<TextView>(R.id.rec_water_view);
        val weight = resources.getStringArray(R.array.weight_identifiers);
        val spinner = findViewById<Spinner>(R.id.weight_spinner);
        val weightVal = findViewById<EditText> (R.id.editText_Weight);

        /*var recommended = weightVal.text.toString().toInt();*/
        /*if (spinner.selectedItem.toString() == "KGS"){
            recommended = recommended * 2;
        }*/
        if (weightVal.text.toString() != "") {
            var recommended = weightVal.text.toString().toInt();
            if (spinner.selectedItem.toString() == "KGS") {
                recommended = recommended * 2;
            }

            var waterRecommended = recommended / 2;

            var inLiters = waterRecommended / 33.814;

            rec_water.setText(waterRecommended.toString() + " Oz or ~" + (inLiters.toFloat()).toString() + "L");
        }
    }

    override fun onPause() {
        super.onPause();

        val sharedPref = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        val editor = sharedPref.edit();

        val weight = resources.getStringArray(R.array.weight_identifiers);
        val spinner = findViewById<Spinner>(R.id.weight_spinner);

        val weightVal = findViewById<EditText> (R.id.editText_Weight);
       // Log.d("CREATION", weightVal.text.toString());
        if (weightVal.text.toString() != "0" && weightVal.text.toString() != "") {
            editor.putInt("Weight", weightVal.text.toString().toInt());
            editor.commit();
        }

        editor.putString("ScaleType", spinner.selectedItem.toString());
        editor.commit();
     }
}