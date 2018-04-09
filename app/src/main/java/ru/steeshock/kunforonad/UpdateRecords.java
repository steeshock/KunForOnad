package ru.steeshock.kunforonad;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by steeshock on 08.04.2018.
 */

public class UpdateRecords extends AppCompatActivity implements View.OnClickListener{

    public static String _ID = "_ID";
    public static String DATE = "DATE";
    public static String STAGE = "STAGE";
    public static String STATE = "STATE";
    public static String SERIES = "SERIES";

    EditText et_Date, et_State, et_Series;
    Spinner sp_stage;

    DBHelper dbHelper = new DBHelper (this);
    SQLiteDatabase db;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_records);

        db = dbHelper.getWritableDatabase();  //открываем для изменения

        et_Date = findViewById(R.id.et_date);
        sp_stage = findViewById(R.id.sp_stage);
        et_State = findViewById(R.id.et_state);
        et_Series = findViewById(R.id.et_series);


        Button btnSave = findViewById(R.id.update);

        btnSave.setOnClickListener(this);

        bundle = getIntent().getExtras();
        et_Date.setText(formatDateAndTime(bundle.getLong(DATE)));
        sp_stage.setSelection((int)bundle.getLong(STAGE));
        et_State.setText(bundle.getString(STATE));
        et_Series.setText(bundle.getString(SERIES));


        Toast.makeText(this, bundle.getString(_ID), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {

        // создаем объект для данных
        ContentValues cv = new ContentValues();




        switch (v.getId()) {

            case R.id.update:

                // получаем данные из полей ввода
                Long stage = sp_stage.getSelectedItemId();
                String readable_stage = sp_stage.getSelectedItem().toString();
                String state = et_State.getText().toString();
                String series = et_Series.getText().toString();


                // подготовим данные для вставки в виде пар: наименование столбца - значение

                cv.put("stage", stage);
                cv.put("state", state);
                cv.put("readable_stage", readable_stage);
                cv.put("series", series);

                db.update("RecordsTable", cv, "_id" + " = " + bundle.getString(_ID), null);
                Toast.makeText(this, "Данные обновлены", Toast.LENGTH_LONG).show();

                db.close();
                finish();
                break;
        }
    }

    public String formatDateAndTime (Long d) {

        return DateUtils.formatDateTime(this,
                d,
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME);
    }
}
