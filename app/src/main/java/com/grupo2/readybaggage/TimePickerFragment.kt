package com.grupo2.readybaggage

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment(val listener:(String) -> Unit) : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private var timeasd: Int = 5

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        //listener("$hourOfDay:$minute")
        listener(String.format("%02d", hourOfDay)+":"+String.format("%02d", minute))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val picker = TimePickerDialog(activity as Context, this, hour, minute, true)
        return picker
    }

    fun setLimita() {
        println("********TESTING PARAMETER BEFORE" )
        timeasd = 9
    }
}