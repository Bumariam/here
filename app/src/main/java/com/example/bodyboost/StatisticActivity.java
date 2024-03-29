package com.example.bodyboost;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class StatisticActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private Calendar startCalendar;
    private Calendar endCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        startCalendar = Calendar.getInstance();
        endCalendar = Calendar.getInstance();
    }




    public void selectStartDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                startCalendar.set(year, month, dayOfMonth);
            }
        }, startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void selectEndDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                endCalendar.set(year, month, dayOfMonth);
            }
        }, endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void showStatistics(View view) {
        // Проверяем, что выбранная конечная дата позже начальной
        if (endCalendar.after(startCalendar)) {
            // Выполняем действия с выбранными датами
            Toast.makeText(this, "Начальная дата: " + startCalendar.getTime() + "\nКонечная дата: " + endCalendar.getTime(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Конечная дата должна быть позже начальной", Toast.LENGTH_SHORT).show();
        }
    }
}
