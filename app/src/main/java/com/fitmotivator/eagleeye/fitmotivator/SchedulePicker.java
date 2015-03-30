package com.fitmotivator.eagleeye.fitmotivator;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.widget.DatePicker;

/**
 * Created by Hafiz on 2/6/2015.
 */
public class SchedulePicker extends FragmentActivity implements DatePickerDialog.OnDateSetListener {

//    showDateDialog(){
//
//        FragmentManager fm = getSupportFragmentManager();
//        TimePickerFragment newFragment = new TimePickerFragment(this);
//        newFragment.show(fm, "date_picker");
//    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){

    }
}
