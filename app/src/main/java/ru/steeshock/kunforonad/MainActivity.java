package ru.steeshock.kunforonad;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    private Calendar dateAndTime = Calendar.getInstance();

    private Spinner sp_stage;
    private EditText et_Date, et_State, et_Series, et_pName, et_pOboz, et_pNumber, et_pDesc,
            et_bName, et_bOboz, et_bNumber, et_bDesc, et_Position, et_positionDesc,
            et_el1_1, et_el2_1, et_el3_1, et_el4_1, et_el1_2, et_el2_2, et_el3_2, et_el4_2,
            et_analysisResult, et_Reason, et_Fault, et_Ai, et_Protocol;

    private KunRecord mKun = new KunRecord();

    // создаем объект для создания и управления версиями БД
    DBHelper dbHelper = new DBHelper (this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

             //INITIALIZING COMPONENTS//

        et_Date = findViewById(R.id.et_date);
        et_State = findViewById(R.id.et_state);
        et_Series = findViewById(R.id.et_series);
        et_pName = findViewById(R.id.et_pName);
        et_pOboz = findViewById(R.id.et_pOboz);
        et_pNumber = findViewById(R.id.et_pNumber);
        et_pDesc = findViewById(R.id.et_pDesc);
        et_bName = findViewById(R.id.et_bName);
        et_bOboz = findViewById(R.id.et_bOboz);
        et_bNumber = findViewById(R.id.et_bNumber);
        et_bDesc = findViewById(R.id.et_bDesc);
        et_Position = findViewById(R.id.et_Position);
        et_positionDesc = findViewById(R.id.et_positionDesc);
        et_el1_1 = findViewById(R.id.et_el1_1);
        et_el2_1 = findViewById(R.id.et_el2_1);
        et_el3_1 = findViewById(R.id.et_el3_1);
        et_el4_1 = findViewById(R.id.et_el4_1);
        et_el1_2 = findViewById(R.id.et_el1_2);
        et_el2_2 = findViewById(R.id.et_el2_2);
        et_el3_2 = findViewById(R.id.et_el3_2);
        et_el4_2 = findViewById(R.id.et_el4_2);
        et_analysisResult = findViewById(R.id.et_analysisResult);
        et_Reason = findViewById(R.id.et_Reason);
        et_Fault = findViewById(R.id.et_Fault);
        et_Ai = findViewById(R.id.et_Ai);
        et_Protocol = findViewById(R.id.et_Protocol);

        sp_stage = findViewById(R.id.sp_stage);

        Button btnClear = findViewById(R.id.clear);
        Button btnSave = findViewById(R.id.save);

        btnClear.setOnClickListener(mOnClearClickListener);
        btnSave.setOnClickListener(mOnSaveClickListener);

        setInitialDateTime();

    }

    // отображаем диалоговое окно для выбора даты

    public void setDate(View v) {
        new DatePickerDialog(MainActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // установка начальных даты и времени

    private void setInitialDateTime() {
        //String editTextDateParam = dateAndTime.get(Calendar.DAY_OF_MONTH) + "." + (dateAndTime.get(Calendar.MONTH) + 1) + "." + dateAndTime.get(Calendar.YEAR);
        //etDate.setText(editTextDateParam);
        et_Date.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }
    // установка обработчика выбора даты

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    // очистка всех полей ввода

    View.OnClickListener mOnClearClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Внимание!")
                    .setMessage("Вся введенная информация будет удалена. Вы уверены?")
                    .setCancelable(false)
                    .setIcon(R.drawable.cancel)
                    .setNegativeButton("ДА",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    clearRecord();
                                }
                            })
                    .setPositiveButton("НЕТ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

            AlertDialog alert = builder.create();
            alert.show();
        }
    };
    public void clearRecord () {
        et_State.getText().clear();
        et_Series.getText().clear();
        et_pName.getText().clear();
        et_pOboz.getText().clear();
        et_pNumber.getText().clear();
        et_pDesc.getText().clear();
        et_bName.getText().clear();
        et_bOboz.getText().clear();
        et_bNumber.getText().clear();
        et_bNumber.getText().clear();
        et_bDesc.getText().clear();
        et_Position.getText().clear();
        et_positionDesc.getText().clear();
        et_el1_1.getText().clear();
        et_el2_1.getText().clear();
        et_el3_1.getText().clear();
        et_el4_1.getText().clear();
        et_el1_2.getText().clear();
        et_el2_2.getText().clear();
        et_el3_2.getText().clear();
        et_el4_2.getText().clear();
        et_analysisResult.getText().clear();
        et_Reason.getText().clear();
        et_Fault.getText().clear();
        et_Ai.getText().clear();
        et_Protocol.getText().clear();
    }

    // сохранения КУНа в БД

    View.OnClickListener mOnSaveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        saveRecord();

        }
    };

    public void saveRecord () {

        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // получаем данные из полей ввода
        String state = et_State.getText().toString();
        String series = et_Series.getText().toString();

        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Log.d(dbHelper.LOG_TAG, "--- Insert in mytable: ---");
        // подготовим данные для вставки в виде пар: наименование столбца - значение

        cv.put("state", state);
        cv.put("series", series);
        // вставляем запись и получаем ее ID
        long rowID = db.insert("mytable", null, cv);
        Log.d(dbHelper.LOG_TAG, "row inserted, ID = " + rowID);

        dbHelper.close();
    }


    //Создание меню

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.open_item:
                Toast.makeText(this, R.string.open, Toast.LENGTH_SHORT).show();break;
            case R.id.save_item:
                saveRecord();
                Toast.makeText(this, R.string.save, Toast.LENGTH_SHORT).show();break;
            case R.id.login_item:
                Toast.makeText(this, R.string.login, Toast.LENGTH_SHORT).show();break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }
}
