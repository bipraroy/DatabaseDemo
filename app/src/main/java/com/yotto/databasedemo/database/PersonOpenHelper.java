package com.yotto.databasedemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by bipra on 17/1/17.
 */

public class PersonOpenHelper extends SQLiteOpenHelper {
    private static  PersonOpenHelper dbHelper;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + PersonContract.PersonTable.TABLE_NAME + " (" +
                    PersonContract.PersonTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    PersonContract.PersonTable.COLUMN_NAME + " TEXT," +
                    PersonContract.PersonTable.COLUMN_PHONE + " TEXT," +
                    PersonContract.PersonTable.COLUMN_AGE+" INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " +  PersonContract.PersonTable.TABLE_NAME;

    public static PersonOpenHelper getInstance(Context context){
        if(dbHelper == null){
            dbHelper = new PersonOpenHelper(context);
        }
        return dbHelper;
    }

    private PersonOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);


    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
