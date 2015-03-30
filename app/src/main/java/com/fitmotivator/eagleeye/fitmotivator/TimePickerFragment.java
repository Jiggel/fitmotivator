package com.fitmotivator.eagleeye.fitmotivator;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;


/**
 * Created by Hafiz on 2/6/2015.
 */
public class TimePickerFragment extends DialogFragment {

    private DatePickerDialog datePicker;
    private DatePickerDialog.OnDateSetListener listener;

    public TimePickerFragment(DatePickerDialog.OnDateSetListener listener){

        this.listener = listener;
    }

    public TimePickerFragment(){}

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        //Use the current time as default values for the picker
        final Calendar cal = Calendar.getInstance();
        int yeah = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        //Create a new instance of TimePickerDialog and return it

        return new DatePickerDialog(getActivity(), listener, yeah,month,day);
    }

}

