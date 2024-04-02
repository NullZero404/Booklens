package com.cvsuimus.emptyviews

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var tvDatePicker : TextView
    private lateinit var btnDatePicker : Button

    private lateinit var tvTime : TextView
    private lateinit var btnTimePicker : Button

    //val spinner: Spinner = findViewById(R.id.spinner)
    private var selected: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //calendar
        tvDatePicker = findViewById(R.id.tvDate)
        btnDatePicker = findViewById(R.id.btnDatePicker)


        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, month, dayOfMonth, year  ->
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            myCalendar.set(Calendar.YEAR, year)
            updateLabel(myCalendar)
        }

        btnDatePicker.setOnClickListener {
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH),
                myCalendar.get(Calendar.YEAR)).show()
        }

        //time
        tvTime = findViewById(R.id.tvTime)
        btnTimePicker = findViewById(R.id.btnTimePicker)

        btnTimePicker.setOnClickListener {
            val currentTime = Calendar.getInstance()
            val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, Minute ->
                tvTime.setText("$hourOfDay:$Minute")
            }, startHour, startMinute, false).show()
        }

        //incident
        /*val spinnerValues = arrayOf("banggaan", "nakawan", "suntukan", "droga", "others")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerValues)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val defaultSelection = "incidents"
        val defaultIndex = spinnerValues.indexOf(defaultSelection)
        spinner.setSelection(defaultIndex)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Handle the selected item
                val selectedItem = spinnerValues[position]

                // Assign the selected value to the variable
                selected = selectedItem

                if (selectedItem != defaultSelection) {
                    // Do something with the selected value if needed
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing here
            }
        }*/


    }
    //calendar
    private fun updateLabel(myCalendar: Calendar){
        val myFormat = "MM-dd-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        tvDatePicker.setText(sdf.format(myCalendar.time))
    }
}