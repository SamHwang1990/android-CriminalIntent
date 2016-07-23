package com.bignerdranch.android.criminalintent;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sam on 16/7/23.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    public static CrimeLab get() {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab();
        }
        return sCrimeLab;
    }

    private CrimeLab() {
        mCrimes = new ArrayList<>();

        for (int i = 0; i < 100; ++i) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime: mCrimes) {
            if (crime.getID() == id) {
                return crime;
            }
        }
        return null;
    }
}
