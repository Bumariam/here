package com.example.bodyboost;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;

public class WaterActivity extends AppCompatActivity {

    private static final String WATER_AMOUNT_KEY = "water_amount";
    private static final int DAILY_GOAL = 2000; // Дневная цель потребления воды (в мл)
    private SharedPreferences sharedPreferences;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        progressBar = findViewById(R.id.progressBar);

        int consumedWaterAmount = sharedPreferences.getInt(WATER_AMOUNT_KEY, 0);
        updateUI(consumedWaterAmount);

        ImageView buttonAddWater = findViewById(R.id.buttonAddWater);
        buttonAddWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWaterAmountDialog();
            }
        });
    }

    private void showWaterAmountDialog() {
        final CharSequence[] items = {"+10 ml", "+20 ml", "+50 ml", "+100 ml"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Water Amount");
        builder.setItems(items, (dialog, which) -> {
            int amount = 0;
            switch (which) {
                case 0:
                    amount = 10;
                    break;
                case 1:
                    amount = 20;
                    break;
                case 2:
                    amount = 50;
                    break;
                case 3:
                    amount = 100;
                    break;
            }
            int updatedWaterAmount = sharedPreferences.getInt(WATER_AMOUNT_KEY, 0) + amount;
            updateUI(updatedWaterAmount);
        });
        builder.show();
    }

    private void updateUI(int waterAmount) {
        int percentage = (int) ((waterAmount / (float) DAILY_GOAL) * 100);

        TextView textViewPercentage = findViewById(R.id.textViewPercentage);
        TextView textViewAmount = findViewById(R.id.tvConsumedWater);

        textViewPercentage.setText(percentage + "%");
        textViewAmount.setText("Amount: " + waterAmount + "ml");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(WATER_AMOUNT_KEY, waterAmount);
        editor.apply();

        // Обновление прогресса ProgressBar
        progressBar.setProgress(percentage);
    }
}
