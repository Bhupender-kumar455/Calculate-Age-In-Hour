package com.example.agecalculatorinhours

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : ComponentActivity() {
    private var selectedDate :TextView? = null
    private var tvHour:TextView? =null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val selectDateBtn = findViewById<Button>(R.id.selectDateButton)
        selectedDate = findViewById(R.id.tvSelectedDate)
                tvHour = findViewById(R.id.tvHours)
        selectDateBtn.setOnClickListener {
            hereYouGo()
        }
    }

    fun hereYouGo() {

        val datePicker = Calendar.getInstance()
        val year = datePicker.get(Calendar.YEAR)
        val month = datePicker.get(Calendar.MONTH)
        val dayOfMonth = datePicker.get(Calendar.DAY_OF_MONTH)

       val dpd = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selecteddate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            selectedDate?.text = selecteddate
            val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selecteddate)
            theDate?.let {
                val selectedDateInHours = (theDate.time/60000)/60
                val currentDate =sdf.parse(sdf.format(System.currentTimeMillis()))
           currentDate?.let{
               val currentDateInHours = (currentDate.time/60000)/60
               val differentInHours =currentDateInHours-selectedDateInHours
               tvHour?.text = differentInHours.toString()
                }
            }
        },year,month,dayOfMonth)
        dpd.datePicker.maxDate =(System.currentTimeMillis()-86400000)
        dpd.show()
    }
}