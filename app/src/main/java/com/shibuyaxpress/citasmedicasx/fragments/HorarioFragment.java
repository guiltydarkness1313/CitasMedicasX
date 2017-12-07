package com.shibuyaxpress.citasmedicasx.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.shibuyaxpress.citasmedicasx.R;
import com.shibuyaxpress.citasmedicasx.models.MyWeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class HorarioFragment extends Fragment {
    private static final String TAG =DatesFragment.class.getSimpleName();
    private Map<String, List<WeekViewEvent>> loadedMonths = new HashMap<>();
    WeekView mWeekView;
    private int startHour = 7;
    public HorarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_horario, container, false);

        mWeekView = (WeekView) v.findViewById(R.id.weekView);

        mWeekView.goToHour(7);

        mWeekView.setMonthChangeListener(new MonthLoader.MonthChangeListener() {
            @Override
            public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                Log.d(TAG, "Year: " + newYear + " | Month: " + newMonth);

                if(!loadedMonths.containsKey(newYear+"/"+newMonth)){
                    List<WeekViewEvent> currentEvents = new ArrayList<>();
                    initialize(newYear, newMonth, currentEvents);
                    loadedMonths.put(newYear+"/"+newMonth, currentEvents);
                }else{
//                    loadedMonths.remove(newYear+"/"+newMonth);    // Clear cache
                }
                return loadedMonths.get(newYear+"/"+newMonth);
            }
        });
        return v;
    }

    private void initialize(final int year, final int month,final List<WeekViewEvent>currentDates) {
        Calendar now = Calendar.getInstance();
        int minHour = 13;
        Calendar startTime = (Calendar) now.clone();
        startTime.set(Calendar.YEAR, 2017);
        startTime.set(Calendar.MONTH, 12 - 1);
        startTime.set(Calendar.DAY_OF_MONTH, 6);
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);

        Calendar endTime = (Calendar) now.clone();
        endTime.set(Calendar.YEAR, 2017);
        endTime.set(Calendar.MONTH, 12 - 1);
        endTime.set(Calendar.DAY_OF_MONTH, 6);
        endTime.set(Calendar.HOUR_OF_DAY, 9);
        endTime.set(Calendar.MINUTE, 40);
        endTime.set(Calendar.SECOND, 0);
        endTime.set(Calendar.MILLISECOND, 0);

        //crear evento weekview
        MyWeekViewEvent weekViewEvent = new MyWeekViewEvent();
        weekViewEvent.setId(1);
        weekViewEvent.setName("Doctor Garcia");
        weekViewEvent.setStartTime(startTime);
        weekViewEvent.setEndTime(endTime);
        weekViewEvent.setLocation("libre");
        weekViewEvent.setColor(Color.parseColor("#00BCD4"));

        currentDates.add(weekViewEvent);
        if(startTime.get(Calendar.HOUR_OF_DAY) < minHour)
            minHour = startTime.get(Calendar.HOUR_OF_DAY);

        mWeekView.notifyDatasetChanged();

        startHour = minHour-1;
        mWeekView.goToHour(startHour);
    }
}
