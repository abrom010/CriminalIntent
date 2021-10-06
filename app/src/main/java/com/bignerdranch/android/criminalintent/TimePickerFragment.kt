package com.bignerdranch.android.criminalintent

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.sql.Time

private const val ARG_TIME = "time"

class TimePickerFragment : DialogFragment() {

    interface Callbacks {
        fun onTimeSelected(time: Time)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val timeSetListener = TimePickerDialog.OnTimeSetListener {
            _: TimePicker, hour: Int, minute: Int ->

            val resultTime = Time(hour, minute, 0)

            targetFragment?.let { fragment ->
                (fragment as Callbacks).onTimeSelected(resultTime)
            }
        }

        val time = arguments?.getSerializable(ARG_TIME) as Time
        return TimePickerDialog(
            requireContext(),
            timeSetListener,
            time.hours,
            time.minutes,
            false
        )
    }

    companion object {
        fun newInstance(time: Time): TimePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TIME, time)
            }

            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }
}