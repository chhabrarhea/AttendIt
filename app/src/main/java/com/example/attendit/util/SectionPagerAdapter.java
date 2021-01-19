package com.example.attendit.util;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.attendit.activities.CalendarFragment;
import com.example.attendit.activities.StudentFragment;


public class SectionPagerAdapter extends FragmentPagerAdapter {


    private long classID;


 public SectionPagerAdapter(FragmentManager fm,final long id)
 {
     super(fm);
     this.classID=id;
 }

    public SectionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = StudentFragment.newInstance(classID);
                break;
            case 1:
                fragment =  CalendarFragment.newInstance(classID);
                break;

        }
        return fragment;
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Students";
            case 1:
                return "Attendance";

        }
        return null;
    }
}