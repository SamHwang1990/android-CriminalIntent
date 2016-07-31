package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.bignerdranch.android.criminalintent.database.CrimeDbSchema.CrimeBaseHelper;
import com.bignerdranch.android.criminalintent.database.CrimeDbSchema.CrimeCursorWrapper;
import com.bignerdranch.android.criminalintent.database.CrimeDbSchema.CrimeDbSchema;
import com.bignerdranch.android.criminalintent.database.CrimeDbSchema.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sam on 16/7/23.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return crimes;
    }

    public Crime getCrime(UUID id) {
        String uuidString = id.toString();

        CrimeCursorWrapper cursor = queryCrimes(CrimeTable.Cols.UUID + " = ?", new String[]{uuidString});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public void addCrime(Crime c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeTable.NAME, null, values);
    }

    public void updateCrime(Crime c) {
        String uuidString = c.getID().toString();
        ContentValues values = getContentValues(c);
        mDatabase.update(CrimeTable.NAME, values, CrimeTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    private static ContentValues getContentValues(Crime c) {
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, c.getID().toString());
        values.put(CrimeTable.Cols.TITLE, c.getTitle());
        values.put(CrimeTable.Cols.DATE, c.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, c.getSolved() ? 1 : 0);
        return values;
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(CrimeTable.NAME, null, whereClause, whereArgs, null, null, null);
        return new CrimeCursorWrapper(cursor);
    }

}
