package com.example.ray.codecollections.view.functionactivity.searchbanner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SearchDQHelper extends SQLiteOpenHelper {
    private  static String name = "records";
    private static Integer version = 1;
    public SearchDQHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists " + name + "(id integer primary key,name varchar(255))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
