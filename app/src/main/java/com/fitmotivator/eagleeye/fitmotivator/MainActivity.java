package com.fitmotivator.eagleeye.fitmotivator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    //Fields

    Button btnWeather;
    int calGoal;
    EditText calNew;
    Integer calories;
    TextView display;
    TextView motMsg;
    TextView txtGoal;
    Button btnDatePicker;

    int counter = 0;


    private Toolbar toolbar;
    NavigationDrawerFragment drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        btnWeather = (Button) findViewById(R.id.btnWeather);

        calNew = (EditText) findViewById(R.id.addCal);
        calGoal = 0;
        calories = 5000;
        display = (TextView) findViewById(R.id.calDisplay);
        display.setText("Remaining goal is: " + calories.toString());
        motMsg = (TextView)findViewById(R.id.motMsg);
        txtGoal = (TextView)findViewById(R.id.txtGoal);
        btnDatePicker = (Button)findViewById(R.id.btnDatePicker);


        txtGoal.setText("Goal: " + calories);

        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = calNew.getText().toString();
                Integer txtInt = Integer.parseInt(txt);
                calories -= txtInt;

                if(calories <= 1200)
                {
                    display.setText("Remaining goal is: " + calories.toString());
                    motMsg.setText("You have almost reached your goal: Good job");

                    if(calories <= 0)
                    {
                        calories = 0;
                        btnWeather.setEnabled(false);
                        display.setText("Remaining goal is: " + calories.toString());
                        motMsg.setText("");
                        Toast.makeText(getApplicationContext(), "CONGRATULATIONS! GOAL REACHED", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    display.setText("Remaining goal is: " + calories.toString());
                }

                calNew.setText("");



            }
        });

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Day_Picker_Activity.class));
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
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

    /**
     * Back button listener.
     * Will close the application if the back button pressed twice.
     */
    public void onBackPressed()
    {
        Intent intent = new Intent(MainActivity.this, ShowWeather.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
