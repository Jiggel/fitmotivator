package com.fitmotivator.eagleeye.fitmotivator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Calories extends ActionBarActivity {


    EditText addGoal, addAge, addWeight, addHeight, addBmi;
    Button btnSaveInfo;

    private Toolbar toolbar;
    NavigationDrawerFragment drawerFragment;

    int counter = 0;

    private static String DEFAULT_DATA = "";
    private static String setGoal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);


        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        addGoal = (EditText) findViewById(R.id.addGoal);
        addAge = (EditText) findViewById(R.id.addAge);
        addWeight = (EditText) findViewById(R.id.addWeight);
        addHeight = (EditText) findViewById(R.id.addHeight);
        addBmi = (EditText) findViewById(R.id.addBmi);

        btnSaveInfo = (Button) findViewById(R.id.btnCalSave);


        SharedPreferences sharedPreferences = getSharedPreferences("MyCalorieInfo", Context.MODE_PRIVATE);
        String goalData = sharedPreferences.getString("goal", DEFAULT_DATA);
        String ageData = sharedPreferences.getString("age", DEFAULT_DATA);
        String weightData = sharedPreferences.getString("weight", DEFAULT_DATA);
        String heightData = sharedPreferences.getString("height", DEFAULT_DATA);
        String bmiData = sharedPreferences.getString("bmi", DEFAULT_DATA);



        if (goalData != null)
        {
            addGoal.setText(goalData);
            setGoal = goalData;
        }
        else
        {
            addGoal.setText("0");
            setGoal = "0";
        }

        if (ageData != null)
        {
            addAge.setText(ageData);
        }
        else
        {
           addAge.setText("0");
        }

        if (weightData != null)
        {
            addWeight.setText(weightData);
        }
        else
        {
            addWeight.setText("0");
        }

        if (heightData != null)
        {
            addHeight.setText(heightData);
        }
        else
        {
            addHeight.setText("0");
        }

        if (bmiData != null)
        {
            addBmi.setText(bmiData);
        }
        else
        {
            addBmi.setText("0");
        }


        btnSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String goal = addGoal.getText().toString();
                String age = addAge.getText().toString();
                String weight = addWeight.getText().toString();
                String height = addHeight.getText().toString();
                String bmi = addBmi.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("MyCalorieInfo", Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(goal.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "You must add a goal", Toast.LENGTH_LONG).show();
                }
                else
                {

                    int tempGoal = Integer.parseInt(goal);
                    if (tempGoal <= 0)
                    {
                        Toast.makeText(getApplicationContext(), "Goal must be higher than 0 " , Toast.LENGTH_LONG).show();
                        addGoal.setTextColor(Color.parseColor("#d32f2f"));
                    }
                    else
                    {
                        editor.putString("goal", goal);
                        editor.commit();
                        addGoal.setTextColor(Color.parseColor("#212121"));
                    }

                }

                if(age.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "You must add your age ", Toast.LENGTH_LONG).show();
                }
                else
                {

                    int tempAge = Integer.parseInt(age);
                    if (tempAge <= 10)
                    {
                        Toast.makeText(getApplicationContext(), "Age must be higher than 10 " , Toast.LENGTH_LONG).show();
                        addGoal.setTextColor(Color.parseColor("#d32f2f"));
                    }
                    else
                    {
                        editor.putString("age", age);
                        editor.commit();
                        addAge.setTextColor(Color.parseColor("#212121"));
                    }

                }

                if(weight.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "You must add your weight ", Toast.LENGTH_LONG).show();
                }
                else
                {

                    int tempWeight = Integer.parseInt(weight);
                    if (tempWeight <= 15 || tempWeight >= 600)
                    {
                        Toast.makeText(getApplicationContext(), "Weight must be higher than 15 and less than 700 " , Toast.LENGTH_LONG).show();
                        addWeight.setTextColor(Color.parseColor("#d32f2f"));
                    }
                    else
                    {
                        editor.putString("weight", weight);
                        editor.commit();
                        addWeight.setTextColor(Color.parseColor("#212121"));
                    }
                }

                if(height.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "You must add your weight ", Toast.LENGTH_LONG).show();
                }
                else
                {

                    Double tempHeight = Double.parseDouble(height);
                    if (tempHeight <= 0.00 || tempHeight >= 5.00)
                    {
                        Toast.makeText(getApplicationContext(), "Height must be higher than 0.00 or less than 5.00 " , Toast.LENGTH_LONG).show();
                        addHeight.setTextColor(Color.parseColor("#d32f2f"));
                    }
                    else
                    {
                        editor.putString("height", height);
                        editor.commit();
                        addHeight.setTextColor(Color.parseColor("#212121"));
                    }
                }

                if(bmi.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "You must add your BMI info ", Toast.LENGTH_LONG).show();
                }
                else
                {

                    int tempBmi = Integer.parseInt(bmi);
                    if (tempBmi <= 0)
                    {
                        Toast.makeText(getApplicationContext(), "BMI must be higher than 0 " , Toast.LENGTH_LONG).show();
                        addBmi.setTextColor(Color.parseColor("#d32f2f"));
                    }
                    else
                    {
                        editor.putString("bmi", bmi);
                        editor.commit();
                        addBmi.setTextColor(Color.parseColor("#212121"));
                    }
                }

                Toast.makeText(getApplicationContext(), "Changes saved ", Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calories, menu);
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


    //Go to main on back button press
    @Override
    public void onBackPressed(){

        Intent intent = new Intent(Calories.this, ShowWeather.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public String getGoal(){

        return setGoal;
    }




}
