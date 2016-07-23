package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by sam on 16/7/23.
 */
public class CrimeListActivity extends SimpleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
