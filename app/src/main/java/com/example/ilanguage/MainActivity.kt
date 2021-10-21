package com.example.ilanguage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        MaterialCalendarView(materialCalendarView)= (MaterialCalendarView) findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
            .setFirstDayOfWeek(Calendar.MONDAY)
            .setMinimumDate(CalendarDay.from(2016, 1, 1))
            .setMaximumDate(CalendarDay.from(2100, 12, 31))
            .setCalendarDisplayMode(CalendarMode.MONTHS)
            .commit();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            public void onDateSelected

        }


    }

}