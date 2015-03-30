package com.fitmotivator.eagleeye.fitmotivator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;


public class splashscreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

       int secondsDelayed = 1;
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
            startActivity(new Intent(splashscreen.this, ShowWeather.class));
           }
       }, secondsDelayed * 3000 );

//        Thread splashTimer = new Thread() {
//            public void run() {
//                try {
//                    sleep(5000);
//                    Intent mainIntent = new Intent("android.intent.action.MAIN");
//                    startActivity(mainIntent);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    finish();
//
//                }
//            }
//        };
//        splashTimer.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splashscreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }
}
