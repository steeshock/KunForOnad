package ru.steeshock.kunforonad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by steeshock on 01.04.2018.
 */

class DBHelper extends SQLiteOpenHelper {

    final String LOG_TAG = "myLogs";

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "myDataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL("create table RecordsTable ("
                + "_id integer primary key autoincrement,"
                + "date integer,"
                + "readable_date text,"
                + "stage integer,"
                + "readable_stage text,"
                + "state text,"
                + "series text" + ");");

        db.execSQL("create table ElementsTable ("
                + "_id integer primary key autoincrement,"
                + "series text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
