package com.bignerdranch.android.criminalintent;

import android.text.format.DateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by sam on 16/7/23.
 */
public class Crime {
    private UUID mID;
    private String mTitle;
    private Date mDate;
    private Boolean mSolved;

    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime(UUID uuid) {
        mID = uuid;
        mDate = new Date();
        mTitle = "";
        mSolved = false;
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

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Boolean getSolved() {
        return mSolved;
    }

    public void setSolved(Boolean solved) {
        mSolved = solved;
    }

    public String getDateFormatted() {
        return DateFormat.format("EEEE, MMM dd, yyyy", this.getDate()).toString();
    }
}
