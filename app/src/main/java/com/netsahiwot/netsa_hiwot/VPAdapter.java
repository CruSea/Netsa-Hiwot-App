package com.netsahiwot.netsa_hiwot;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Sammie on 8/22/2016.
 */

public class VPAdapter extends FragmentPagerAdapter {
    private String fragments[] = {"Insert", "Read", "Update", "Delete"};

    public VPAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int postion) {
        switch (postion) {
            case 0:
                return new Insert_Frag();
            case 1:
                return new Read_Frag();
            case 2:
                return new Update_Frag();
            case 3:
                return new Delete_Frag();
            default:
                return new Insert_Frag();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}