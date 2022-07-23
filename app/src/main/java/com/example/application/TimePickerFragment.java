package com.example.application;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),this,mHour,mMinute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        //get reference od host activity Textview widget
        TextView textView = (TextView) getActivity().findViewById(R.id.text_time);

        // Get the AM or PM for current time
        String aMpM ="AM";
        if (hourOfDay >11){
            aMpM = "PM";
        }

        // Get the 24 hour time format to 12 hour time format
        int currentHour;
        if (hourOfDay > 11){
            currentHour = hourOfDay -12 ;
        }else {
            currentHour = hourOfDay;
        }

        textView.setText(hourOfDay +":"+minute +" "+ aMpM +" ");
    }
}
