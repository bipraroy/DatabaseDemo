package com.yotto.databasedemo.database;

import android.provider.BaseColumns;

/**
 * Created by bipra on 17/1/17.
 */

public final class PersonContract {

    private PersonContract(){}

    public static class PersonTable implements BaseColumns{
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_AGE = "age";

    }
}
