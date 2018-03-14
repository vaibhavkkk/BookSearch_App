package com.example.vaibhav.booksearch;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.LinearLayout;

/**
 * Created by vaibhav on 11-04-2017.
 */

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                Category tab3 = new Category();
                return tab3;
            case 1:
                Home tab1 = new Home();
                return tab1;
            case 2:
                Top_Books tab2 = new Top_Books();
                return tab2;
            case 3:
                Top_Authors tab4 = new Top_Authors();
                return tab4;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
    @Override
    public CharSequence getPageTitle(int position){
        String name=null;
        if(position==0){
            name="CATEGORY";
        }
        if(position==1){
            name="Home";
        }
        else if(position==2){
            name="Top Books";
        }
        else if(position==3){
            name="Top Authors";
        }
        return name;
    }
}