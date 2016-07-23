package com.bignerdranch.android.criminalintent;

import java.util.UUID;

/**
 * Created by sam on 16/7/23.
 */
public class Crime {
    private UUID mID;
    private String mTitle;

    public Crime() {
        mID = UUID.randomUUID();
    }

    public UUID getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
