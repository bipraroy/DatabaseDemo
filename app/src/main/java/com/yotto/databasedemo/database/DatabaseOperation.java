package com.yotto.databasedemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yotto.databasedemo.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bipra on 17/1/17.
 */

public class DatabaseOperation {
    private Context context;
    public DatabaseOperation(Context context){
        this.context = context;
    }

    public long  addPerson(Person person){
        SQLiteDatabase db = PersonOpenHelper.getInstance(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PersonContract.PersonTable.COLUMN_NAME,person.getName());
        values.put(PersonContract.PersonTable.COLUMN_PHONE,person.getPhone());
        values.put(PersonContract.PersonTable.COLUMN_AGE,person.getAge());
        long effectedRows = db.insert(PersonContract.PersonTable.TABLE_NAME,null,values);
        db.close();
        return effectedRows;
    }

    public List<Person> getPerson(){
        List<Person> personList = new ArrayList<>();
        SQLiteDatabase db = PersonOpenHelper.getInstance(context).getReadableDatabase();
        String[] projection= {
                PersonContract.PersonTable.COLUMN_NAME,
                PersonContract.PersonTable.COLUMN_PHONE,
                PersonContract.PersonTable.COLUMN_AGE
        };
        String sortOrder = PersonContract.PersonTable.COLUMN_AGE + " DESC";
        Cursor cursor = db.query(
                PersonContract.PersonTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(PersonContract.PersonTable.COLUMN_NAME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(PersonContract.PersonTable.COLUMN_PHONE));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow(PersonContract.PersonTable.COLUMN_AGE));
            Person person = new Person();
            person.setName(name);
            person.setPhone(phone);
            person.setAge(age);
            personList.add(person);
        }
        return personList;
    }


}
