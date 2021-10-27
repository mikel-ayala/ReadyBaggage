package com.grupo2.readybaggage

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(val listener: (day: Int, month: Int, year: Int) -> Unit) :
    DialogFragment(), DatePickerDialog.OnDateSetListener {

    var timeInMs: Int = 1
    val minDateList = mutableListOf<Int>()
    val maxDateList = mutableListOf<Int>()

    //val c = Calendar.getInstance()
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        listener(day, month+1, year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val picker = DatePickerDialog(activity as Context, this, year, month, day)
        picker.datePicker.minDate = c.timeInMillis
        if (!minDateList.isEmpty()) {
            c.set(Calendar.DAY_OF_MONTH,minDateList[0])
            c.set(Calendar.MONTH,minDateList[1]-1)
            c.set(Calendar.YEAR,minDateList[2])
            picker.datePicker.minDate = c.timeInMillis
        }
        if (!maxDateList.isEmpty()) {
                c.set(Calendar.DAY_OF_MONTH,maxDateList[0])
                c.set(Calendar.MONTH,maxDateList[1]-1)
                c.set(Calendar.YEAR,maxDateList[2])
            picker.datePicker.maxDate = c.timeInMillis
        }
        return picker
    }

    fun setDateMinMaxLimit(elementId: Int, day: Int, month: Int, year: Int) {

        if (elementId != null) {
            if (day != null && month != null && year != null) {
                if (elementId == 1) {
                    maxDateList.clear()
                    maxDateList.add(day)
                    maxDateList.add(month)
                    maxDateList.add(year)
                } else if (elementId == 2) {
                    minDateList.clear()
                    minDateList.add(day)
                    minDateList.add(month)
                    minDateList.add(year)
                }
            }
        }
    }



}