package com.fitmotivator.eagleeye.fitmotivator;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;


public class Day_Picker_Activity extends ActionBarActivity {

    //Fields
    private CheckBox chkSun, chkMon, chkTue, chkWed, chkThur, chkFri, chkSat;
    private Button btnSave;

    public NavigationDrawerFragment getSchedule;

    private Toolbar toolbar;

    private String sun, mon, tue, wed, thur, fri, sat;
    private final static String DEFAULT_DAY = "N/A";

    String weekDay;
    int counter = 0;
    int mId;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day__picker_);


        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);


        chkSun = (CheckBox) findViewById(R.id.chk_Sun);
        chkMon = (CheckBox) findViewById(R.id.chk_Mon);
        chkTue = (CheckBox) findViewById(R.id.chk_Tue);
        chkWed = (CheckBox) findViewById(R.id.chk_Wed);
        chkThur = (CheckBox) findViewById(R.id.chk_Thur);
        chkFri = (CheckBox) findViewById(R.id.chk_Fri);
        chkSat = (CheckBox) findViewById(R.id.chk_Sat);

        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);

        Calendar calendar = Calendar.getInstance();
        weekDay = dayFormat.format(calendar.getTime());

        Random rand = new Random();
        int n = 5;

        //Make a sharedprefrence object to create a datastore
        SharedPreferences sharedPreferences = getSharedPreferences("MySchedule", Context.MODE_PRIVATE);
        String day1 = sharedPreferences.getString("Day1", DEFAULT_DAY);
        String day2 = sharedPreferences.getString("Day2", DEFAULT_DAY);
        String day3 = sharedPreferences.getString("Day3", DEFAULT_DAY);
        String day4 = sharedPreferences.getString("Day4", DEFAULT_DAY);
        String day5 = sharedPreferences.getString("Day5", DEFAULT_DAY);
        String day6 = sharedPreferences.getString("Day6", DEFAULT_DAY);
        String day7 = sharedPreferences.getString("Day7", DEFAULT_DAY);


        if (day1.equals(DEFAULT_DAY)) {
            chkSun.setChecked(false);
        } else {
            chkSun.setChecked(true);
            if (day1.equals(weekDay) && n == 5) {
                Toast.makeText(this, "Go for a run, it's perfect weather today " + day1 + " and temp is " + n, Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

                NotificationCompat.Builder noti = new NotificationCompat.Builder(this)
                        .setContentTitle("Content Title")
                        .setSmallIcon(R.drawable.ic_sport)
                        .setContentIntent(pIntent);

                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                String[] events = new String[6];
                //Sets a title for the inbox in expanded layout
                inboxStyle.setBigContentTitle("Testing what this is:");

                //Moves events into the expanded layout
                for (int i = 0; i < events.length; i++)
                {
                    inboxStyle.addLine(events[i]);
                }

                //Moves the expanded layout object into the notification object
                noti.setStyle(inboxStyle);

                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(this, ShowWeather.class);

                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(ShowWeather.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                noti.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // mId allows you to update the notification later on.
                mNotificationManager.notify(mId, noti.build());


            }

        }

        if (day2.equals(DEFAULT_DAY)) {
            chkMon.setChecked(false);

        } else {
            chkMon.setChecked(true);
            if (day2.equals(weekDay) && n == 5) {
                Toast.makeText(this, "Go for a run, it's perfect weather today " + day2 + " and temp is " + n, Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

                NotificationCompat.Builder noti = new NotificationCompat.Builder(this)
                        .setTicker("Lovely weather today")
                        .setContentTitle("Content Title")
                        .setContentTitle("It's perfect weather today @" + n + " temp")
                        .setContentText("A perfect day to go for running. It's beautiful outside")
                        .setSmallIcon(R.drawable.ic_sport)
                        .setContentIntent(pIntent);

                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                String[] events = new String[6];
                //Sets a title for the inbox in expanded layout
                inboxStyle.setBigContentTitle("Testing what this is:");

                //Moves events into the expanded layout
                for (int i = 0; i < events.length; i++)
                {
                    inboxStyle.addLine(events[i]);
                }

                //Moves the expanded layout object into the notification object
                noti.setStyle(inboxStyle);

                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(this, ShowWeather.class);

                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(ShowWeather.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );

                noti.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // mId allows you to update the notification later on.
                mNotificationManager.notify(mId, noti.build());

            }

        }

        if (day3.equals(DEFAULT_DAY)) {
            chkTue.setChecked(false);

        } else {
            chkTue.setChecked(true);
            if (day3.equals(weekDay) && n == 5) {
                Toast.makeText(this, "Go for a run, it's perfect weather today " + day3 + " and temp is " + n, Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

                NotificationCompat.Builder noti = new NotificationCompat.Builder(this)
                        .setTicker("Lovely weather today")
                        .setContentTitle("Content Title")
                        .setContentTitle("It's perfect weather today @" + n + " temp")
                        .setContentText("A perfect day to go for running. It's beautiful outside")
                        .setSmallIcon(R.drawable.ic_sport)
                        .setContentIntent(pIntent);

                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                String[] events = new String[6];
                //Sets a title for the inbox in expanded layout
                inboxStyle.setBigContentTitle("Testing what this is:");

                //Moves events into the expanded layout
                for (int i = 0; i < events.length; i++)
                {
                    inboxStyle.addLine(events[i]);
                }

                //Moves the expanded layout object into the notification object
                noti.setStyle(inboxStyle);

                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(this, ShowWeather.class);

                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(ShowWeather.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );

                noti.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // mId allows you to update the notification later on.
                mNotificationManager.notify(mId, noti.build());
            }
        }

        if (day4.equals(DEFAULT_DAY)) {
            chkWed.setChecked(false);
        } else {
            chkWed.setChecked(true);
            if (day4.equals(weekDay) && n == 5) {
                Toast.makeText(this, "Go for a run, it's perfect weather today " + day4 + " and temp is " + n, Toast.LENGTH_LONG).show();
            }
        }

        if (day5.equals(DEFAULT_DAY)) {
            chkThur.setChecked(false);
        } else {
            chkThur.setChecked(true);
            if (day5.equals(weekDay) && n == 5) {
                Toast.makeText(this, "Go for a run, it's perfect weather today " + day5 + " and temp is " + n, Toast.LENGTH_LONG).show();
            }
        }

        if (day6.equals(DEFAULT_DAY)) {
            chkFri.setChecked(false);
        } else {
            chkFri.setChecked(true);
            if (day6.equals(weekDay) && n == 5) {
                Toast.makeText(this, "Go for a run, it's perfect weather today " + day6 + " and temp is " + n, Toast.LENGTH_LONG).show();
            }
        }


        if (day7.equals(DEFAULT_DAY)) {
            chkSat.setChecked(false);
        } else {
            chkSat.setChecked(true);
            if (day7.equals(weekDay) && n == 5) {
                Toast.makeText(this, "Go for a run, it's perfect weather today " + day7 + " and temp is " + n, Toast.LENGTH_LONG).show();
            }
        }


        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("MySchedule", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (chkSun.isChecked()) {
                    sun = chkSun.getText().toString();
                    editor.putString("Day1", sun);
                    editor.commit();
                } else {
                    editor.remove("Day1");
                    editor.commit();
                }

                if (chkMon.isChecked()) {
                    mon = chkMon.getText().toString();
                    editor.putString("Day2", mon);
                    editor.commit();
                } else {
                    editor.remove("Day2");
                    editor.commit();
                }

                if (chkTue.isChecked()) {
                    tue = chkTue.getText().toString();
                    editor.putString("Day3", tue);
                    editor.commit();
                } else {
                    editor.remove("Day3");
                    editor.commit();
                }

                if (chkWed.isChecked()) {
                    wed = chkWed.getText().toString();
                    editor.putString("Day4", wed);
                    editor.commit();
                } else {
                    editor.remove("Day4");
                    editor.commit();
                }

                if (chkThur.isChecked()) {
                    thur = chkThur.getText().toString();
                    editor.putString("Day5", thur);
                    editor.commit();
                } else {
                    editor.remove("Day5");
                    editor.commit();
                }

                if (chkFri.isChecked()) {
                    fri = chkFri.getText().toString();
                    editor.putString("Day6", fri);
                    editor.commit();
                } else {
                    editor.remove("Day6");
                    editor.commit();
                }

                if (chkSat.isChecked()) {
                    sat = chkSat.getText().toString();
                    editor.putString("Day7", sat);
                    editor.commit();

                } else {
                    editor.remove("Day7");
                    editor.commit();
                }

                Toast.makeText(getApplicationContext(), "Changes saved successfully ", Toast.LENGTH_LONG).show();
            }


        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_day__picker_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }


    protected void onResume() {
        super.onResume();
        counter++;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.getInt("counter", counter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getInt("counter");
    }

    //Go to main on back button press
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Day_Picker_Activity.this, ShowWeather.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
