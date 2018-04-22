package ru.steeshock.kunforonad;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Calendar dateAndTime = Calendar.getInstance();

    public Spinner sp_stage, sp_denyTypo;
    public EditText et_Date, et_State, et_Series, et_pName, et_pOboz, et_pNumber, et_pDesc,
            et_bName, et_bOboz, et_bNumber, et_bDesc, et_Position, et_positionDesc, et_author,
            et_el1_1, et_el2_1, et_el3_1, et_el4_1, et_el1_2, et_el2_2, et_el3_2, et_el4_2,
            et_analysisResult, et_reason, et_fault, et_ai, et_protocol;
    public RadioGroup radioGroup;
    public RadioButton checkedRadioButton;

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
        et_author = findViewById(R.id.et_author);
        et_el1_1 = findViewById(R.id.et_el1_1);
        et_el2_1 = findViewById(R.id.et_el2_1);
        et_el3_1 = findViewById(R.id.et_el3_1);
        et_el4_1 = findViewById(R.id.et_el4_1);
        et_el1_2 = findViewById(R.id.et_el1_2);
        et_el2_2 = findViewById(R.id.et_el2_2);
        et_el3_2 = findViewById(R.id.et_el3_2);
        et_el4_2 = findViewById(R.id.et_el4_2);
        et_analysisResult = findViewById(R.id.et_analysisResult);
        et_reason = findViewById(R.id.et_Reason);
        et_fault = findViewById(R.id.et_Fault);
        et_ai = findViewById(R.id.et_Ai);
        et_protocol = findViewById(R.id.et_Protocol);

        sp_stage = findViewById(R.id.sp_stage);
        sp_denyTypo = findViewById(R.id.sp_denyTypo);

        radioGroup = findViewById(R.id.rg);
        radioGroup.check(R.id.rb1);

        checkedRadioButton = findViewById(R.id.rb1);



        Button btnClear = findViewById(R.id.clear);
        Button btnSave = findViewById(R.id.save);

        btnClear.setOnClickListener(this);
        btnSave.setOnClickListener(this);

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
        //String editTextDateParam = dateAndTime.get(Calendar.DAY_OF_MONTH)
        // + "." + (dateAndTime.get(Calendar.MONTH) + 1)
        // + "." + dateAndTime.get(Calendar.YEAR);
        //etDate.setText(editTextDateParam);
        et_Date.setText(formatDateAndTime(dateAndTime.getTimeInMillis()));
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

    // работа с кнопками

        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.clear:
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
                    break;

                case R.id.save:
                    saveRecord();
                    break;
            }
        }

    // очистка записей


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
        et_author.getText().clear();
        et_el1_1.getText().clear();
        et_el2_1.getText().clear();
        et_el3_1.getText().clear();
        et_el4_1.getText().clear();
        et_el1_2.getText().clear();
        et_el2_2.getText().clear();
        et_el3_2.getText().clear();
        et_el4_2.getText().clear();
        et_analysisResult.getText().clear();
        et_reason.getText().clear();
        et_fault.getText().clear();
        et_ai.getText().clear();
        et_protocol.getText().clear();
    }

    // сохранения КУНа в БД


    public void saveRecord () {

        if (!isFieldsNotEmpty()) {

            Calendar currentDateAndTime = Calendar.getInstance();

            // создаем объект для данных
            ContentValues cv = new ContentValues();

            // получаем данные из полей ввода
            Long date = currentDateAndTime.getTimeInMillis();
            String readable_date = formatDateAndTime(currentDateAndTime.getTimeInMillis());
            Long stage = sp_stage.getSelectedItemId();
            String readable_stage = sp_stage.getSelectedItem().toString();
            Long deny_Type = sp_denyTypo.getSelectedItemId();
            String readable_deny_Type = sp_denyTypo.getSelectedItem().toString();
            String state = et_State.getText().toString();
            String series = et_Series.getText().toString();

            //PRIBOR
            String pName = et_pName.getText().toString();
            String pOboz = et_pOboz.getText().toString();
            String pNumber = et_pNumber.getText().toString();
            String pDesc = et_pDesc.getText().toString();

            //BLOCK
            String bName = et_bName.getText().toString();
            String bOboz = et_bOboz.getText().toString();
            String bNumber = et_bNumber.getText().toString();
            String bDesc = et_bDesc.getText().toString();

            //position
            String position = et_Position.getText().toString();
            String positionDesc = et_positionDesc.getText().toString();

            //analys, author
            String analysisResult = et_analysisResult.getText().toString();
            String author = et_author.getText().toString();

            //reason, fault
            String reason = et_reason.getText().toString();
            String fault = et_fault.getText().toString();

            //get number of ToDolist
            Integer todo = radioGroup.getCheckedRadioButtonId();
            checkedRadioButton = findViewById(todo);
            String todo_desc = checkedRadioButton.getTransitionName() + " " + et_ai.getText().toString() + et_protocol.getText().toString();

            //PA,AI
            String ai = et_ai.getText().toString();
            String protokol = et_protocol.getText().toString();

            Log.d(dbHelper.LOG_TAG, "Radio Button ID: " + checkedRadioButton.getTransitionName());
            //Log.d(dbHelper.LOG_TAG, "TIME: " + date);

            // подключаемся к БД
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // подготовим данные для вставки в виде пар: наименование столбца - значение

            cv.put("date", date);
            cv.put("readable_date", readable_date);
            cv.put("stage", stage);
            cv.put("readable_stage", readable_stage);
            cv.put("state", state);
            cv.put("series", series);
            cv.put("deny_Type", deny_Type);
            cv.put("readable_deny_Type", readable_deny_Type);
            cv.put("pName", pName);
            cv.put("pOboz", pOboz);
            cv.put("pNumber", pNumber);
            cv.put("pDesc", pDesc);
            cv.put("bName", bName);
            cv.put("bOboz", bOboz);
            cv.put("bNumber", bNumber);
            cv.put("bDesc", bDesc);
            cv.put("position", position);
            cv.put("positionDesc", positionDesc);
            cv.put("analysisResult", analysisResult);
            cv.put("author", author);
            cv.put("reason", reason);
            cv.put("fault", fault);
            cv.put("todo", todo);
            cv.put("todo_desc", todo_desc);
            cv.put("ai", ai);
            cv.put("protokol", protokol);

            // вставляем запись и получаем ее ID
            long rowID = db.insert("RecordsTable", null, cv);
            Log.d(dbHelper.LOG_TAG, "row inserted, ID = " + rowID);

            Integer record_id = (int)rowID;  //ID КУНа, в который будем записывать элементы

            //добавляем ЭЛЕМЕНТЫ в БД

            ContentValues cv_elements = new ContentValues();

            if (!TextUtils.isEmpty(et_el1_1.getText()))
            {
                String el_name = et_el1_1.getText().toString();
                String el_extra = et_el1_2.getText().toString();

                cv_elements.put("el_name", el_name);
                cv_elements.put("el_extra", el_extra);
                cv_elements.put("record_id", record_id);

                long rowID_EL = db.insert("ElementsTable", null, cv_elements);
                Log.d(dbHelper.LOG_TAG, "row inserted, ID = " + rowID_EL);
            }

            if (!TextUtils.isEmpty(et_el2_1.getText()))
            {
                String el_name = et_el2_1.getText().toString();
                String el_extra = et_el2_2.getText().toString();

                cv_elements.put("el_name", el_name);
                cv_elements.put("el_extra", el_extra);
                cv_elements.put("record_id", record_id);

                long rowID_EL = db.insert("ElementsTable", null, cv_elements);
                Log.d(dbHelper.LOG_TAG, "row inserted, ID = " + rowID_EL);
            }

            if (!TextUtils.isEmpty(et_el3_1.getText()))
            {
                String el_name = et_el3_1.getText().toString();
                String el_extra = et_el3_2.getText().toString();

                cv_elements.put("el_name", el_name);
                cv_elements.put("el_extra", el_extra);
                cv_elements.put("record_id", record_id);

                long rowID_EL = db.insert("ElementsTable", null, cv_elements);
                Log.d(dbHelper.LOG_TAG, "row inserted, ID = " + rowID_EL);
            }

            if (!TextUtils.isEmpty(et_el4_1.getText()))
            {
                String el_name = et_el4_1.getText().toString();
                String el_extra = et_el4_2.getText().toString();

                cv_elements.put("el_name", el_name);
                cv_elements.put("el_extra", el_extra);
                cv_elements.put("record_id", record_id);

                long rowID_EL = db.insert("ElementsTable", null, cv_elements);
                Log.d(dbHelper.LOG_TAG, "row inserted, ID = " + rowID_EL);
            }

            dbHelper.close();

        } else {
            showFieldsIsEmptyAlertDialog();
        }
    }

    // форматировать ДАТУ и ВРЕМЯ из МИЛИСЕКУНД в СТРОКУ
    public String formatDateAndTime (Long d) {

            return DateUtils.formatDateTime(this,
                    d,
                    DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                            | DateUtils.FORMAT_SHOW_TIME);
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
            case R.id.open_records_item:
                Intent openShowRecords = new Intent(MainActivity.this, ShowRecords.class);
                startActivity (openShowRecords);
                Toast.makeText(this, R.string.open_records, Toast.LENGTH_SHORT).show();break;
            case R.id.open_elements_item:
                Intent openShowElements = new Intent(MainActivity.this, ShowElements.class);
                startActivity (openShowElements);
                Toast.makeText(this, R.string.open_elements, Toast.LENGTH_SHORT).show();break;
            case R.id.save_item:
                saveRecord();
                Toast.makeText(this, R.string.save, Toast.LENGTH_SHORT).show();break;
            case R.id.login_item:
                Toast.makeText(this, R.string.login, Toast.LENGTH_SHORT).show();break;

            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void fillTable () {

        // заполнить таблицу тестовыми записями

    }

    public boolean isFieldsNotEmpty () {

        boolean flag = true;

        if(TextUtils.isEmpty(et_State.getText())){et_State.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_Series.getText())){et_Series.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_pName.getText())){et_pName.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_pOboz.getText())){et_pOboz.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_pNumber.getText())){et_pNumber.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_pDesc.getText())){et_pDesc.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_bName.getText())){et_bName.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_bOboz.getText())){et_bOboz.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_bNumber.getText())){et_bNumber.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_bDesc.getText())){et_bDesc.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_Position.getText())){et_Position.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_positionDesc.getText())){et_positionDesc.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_analysisResult.getText())){et_analysisResult.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_author.getText())){et_author.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_reason.getText())){et_reason.setError("введите данные"); flag = false;}
        if(TextUtils.isEmpty(et_fault.getText())){et_fault.setError("введите данные"); flag = false;}

        return flag;
    }

    public  void showFieldsIsEmptyAlertDialog () {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Внимание!")
                .setMessage("Не все поля заполнены! Пожалуйста, заполните указанные поля.")
                .setCancelable(false)
                .setIcon(R.drawable.warning)
                .setPositiveButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = builder.create();
        alert.show();



    }
}
