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


        db.execSQL("PRAGMA foreign_keys = 0");

        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями

        db.execSQL("create table RecordsTable ("
                + "_id integer primary key autoincrement,"
                + "date integer,"
                + "readable_date text,"
                + "stage integer,"
                + "readable_stage text,"
                + "state text,"
                + "deny_Type integer,"
                + "readable_deny_Type text,"
                + "pName text,"
                + "pOboz text,"
                + "pNumber text,"
                + "pDesc text,"
                + "bName text,"
                + "bOboz text,"
                + "bNumber text,"
                + "bDesc text,"
                + "position text,"
                + "positionDesc text,"
                + "analysisResult text,"
                + "author text,"
                + "reason text,"
                + "fault text,"
                + "todo integer,"
                + "todo_desc text,"
                + "ai text,"
                + "protokol text,"
                + "series text" + ");");

        db.execSQL("create table ElementsTable ("
                + "_id integer primary key autoincrement,"
                + "record_id integer,"
                + "el_name text,"
                + "el_extra text,"
                + "FOREIGN KEY (record_id) REFERENCES RecordsTable(_id)" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
