package com.example.bodyboost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

public class StatisticActivity extends AppCompatActivity {

    private TextView foodTextView, waterTextView, sleepTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        // Получение данных из SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        // Пример получения данных из SharedPreferences
        String totalCaloriesValue = sharedPref.getString("totalCaloriesValue", "");
        int tvConsumedWater = sharedPref.getInt("tvConsumedWater", 0);
        String previousSleepDuration = sharedPref.getString("previousSleepDuration", "");

        // Установка текста для foodTextView, waterTextView, sleepTextView
        foodTextView.setText(totalCaloriesValue);
        waterTextView.setText(String.valueOf(tvConsumedWater));
        sleepTextView.setText(previousSleepDuration);
    }
}
