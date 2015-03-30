package com.fitmotivator.eagleeye.fitmotivator;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements fitAdapter.ClickListerner {

    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;
    private boolean isDrawerOpened = false;

    private RecyclerView recyclerView;

    private fitAdapter adapter;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserLearnedDrawer = Boolean.valueOf(readFromPreference(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

        adapter = new fitAdapter(getActivity(), getData());

        adapter.setClickListerner(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    public static List<fitMotInformation> getData() {

        List<fitMotInformation> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_weather, R.drawable.ic_schedule, R.drawable.ic_calories, R.drawable.ic_sport};
        String[] titles = {"Weather", "Schedule", "Calories", "Sport"};

        for (int i = 0; i < icons.length && i < titles.length; i++) {
            fitMotInformation current = new fitMotInformation();
            current.iconId = icons[i % icons.length];
            current.title = titles[i % titles.length];
            data.add(current);
        }

        return data;
    }


    public void setUp(int fragment_navigation_drawer, DrawerLayout drawerLayout, Toolbar toolbar) {

        containerView = getActivity().findViewById(R.id.fragment_navigation_drawer);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_closed) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreference(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }


            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                Log.d("VIVZ", "offset " + slideOffset);
            }
        };


        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }


    public static void saveToPreference(Context context, String preferenceName, String preferenceValue) {

        SharedPreferences sharePreference = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePreference.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }


    public static String readFromPreference(Context context, String preferenceName, String defaultValue) {

        SharedPreferences sharePreference = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePreference.edit();
        return sharePreference.getString(preferenceName, defaultValue);
    }


    //Activity switching from drawer
    @Override
    public void itemClicked(View view, int position) {

        if(position == 0){
            startActivity(new Intent(getActivity(), ShowWeather.class));
            position = 0;
        }
        else if(position == 1){
            startActivity(new Intent(getActivity(), Day_Picker_Activity.class));
            position = 0;
//            loadData();
        }
        else if(position == 2){
            startActivity(new Intent(getActivity(), Calories.class));
            position = 0;
        }
        else if (position == 3)
        {
            startActivity(new Intent(getActivity(), MainActivity.class));
        }

    }


}
