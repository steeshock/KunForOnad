package ru.steeshock.kunforonad;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    public static String DENY_TYPO = "DENY_TYPO";
    public static String P_NAME = "P_NAME";
    public static String P_OBOZ = "P_OBOZ";
    public static String P_NUMBER = "P_NUMBER";
    public static String P_DESC = "P_DESC";
    public static String B_NAME = "B_NAME";
    public static String B_OBOZ = "B_OBOZ";
    public static String B_NUMBER = "B_NUMBER";
    public static String B_DESC = "B_DESC";
    public static String POSITION = "POSITION";
    public static String POSITION_DESC = "POSITION_DESC";
    public static String ANALYSIS_RESULT = "ANALYSIS_RESULT";
    public static String AUTHOR = "AUTHOR";
    public static String REASON = "REASON";
    public static String FAULT = "FAULT";
    public static String TODO = "TODO";
    public static String TODO_DESC = "TODO_DESC";
    public static String AI = "AI";
    public static String PROTOKOL = "PROTOKOL";

    public int el1_id, el2_id, el3_id, el4_id;


    EditText et_Date, et_State, et_Series, et_pName, et_pOboz, et_pNumber, et_pDesc,
            et_bName, et_bOboz, et_bNumber, et_bDesc, et_Position, et_positionDesc,
            et_analysisResult, et_author, et_reason, et_fault, et_ai, et_protocol,
            et_el1_1, et_el2_1, et_el3_1, et_el4_1, et_el1_2, et_el2_2, et_el3_2, et_el4_2;
    Spinner sp_stage, sp_denyTypo;
    RadioGroup radioGroup;
    RadioButton checkedRadioButton;
    DBHelper dbHelper = new DBHelper (this);
    SQLiteDatabase db;
    Cursor c;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_records);

        bundle = getIntent().getExtras();

        db = dbHelper.getWritableDatabase(); //открываем для изменения
        c = db.query("ElementsTable", null, "record_id" + " = " + bundle.getString(_ID), null, null, null, null);

        et_Date = findViewById(R.id.et_date);
        sp_stage = findViewById(R.id.sp_stage);
        et_State = findViewById(R.id.et_state);
        et_Series = findViewById(R.id.et_series);
        sp_denyTypo = findViewById(R.id.sp_denyTypo);
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
        et_analysisResult = findViewById(R.id.et_analysisResult);
        et_author = findViewById(R.id.et_author);
        et_reason = findViewById(R.id.et_Reason);
        et_fault = findViewById(R.id.et_Fault);
        radioGroup = findViewById(R.id.rg);
        et_ai = findViewById(R.id.et_Ai);
        et_protocol = findViewById(R.id.et_Protocol);
        et_el1_1 = findViewById(R.id.et_el1_1);
        et_el2_1 = findViewById(R.id.et_el2_1);
        et_el3_1 = findViewById(R.id.et_el3_1);
        et_el4_1 = findViewById(R.id.et_el4_1);
        et_el1_2 = findViewById(R.id.et_el1_2);
        et_el2_2 = findViewById(R.id.et_el2_2);
        et_el3_2 = findViewById(R.id.et_el3_2);
        et_el4_2 = findViewById(R.id.et_el4_2);

        Button btnSave = findViewById(R.id.update);

        btnSave.setOnClickListener(this);

        et_Date.setText(formatDateAndTime(bundle.getLong(DATE)));
        sp_stage.setSelection((int)bundle.getLong(STAGE));
        et_State.setText(bundle.getString(STATE));
        et_Series.setText(bundle.getString(SERIES));
        sp_denyTypo.setSelection((int)bundle.getLong(DENY_TYPO));
        et_pName.setText(bundle.getString(P_NAME));
        et_pOboz.setText(bundle.getString(P_OBOZ));
        et_pNumber.setText(bundle.getString(P_NUMBER));
        et_pDesc.setText(bundle.getString(P_DESC));
        et_bName.setText(bundle.getString(B_NAME));
        et_bOboz.setText(bundle.getString(B_OBOZ));
        et_bNumber.setText(bundle.getString(B_NUMBER));
        et_bDesc.setText(bundle.getString(B_DESC));
        et_Position.setText(bundle.getString(POSITION));
        et_positionDesc.setText(bundle.getString(POSITION_DESC));
        et_analysisResult.setText(bundle.getString(ANALYSIS_RESULT));
        et_author.setText(bundle.getString(AUTHOR));
        et_reason.setText(bundle.getString(REASON));
        et_fault.setText(bundle.getString(FAULT));
        et_ai.setText(bundle.getString(AI));
        et_protocol.setText(bundle.getString(PROTOKOL));


        Log.d(dbHelper.LOG_TAG, "число элементов = " + c.getCount());


        for (int i = 0; i < c.getCount(); i++) {

            c.moveToPosition(i);

            switch (i) {
                case 0:
                    et_el1_1.setText(c.getString(2));
                    et_el1_2.setText(c.getString(3));
                    el1_id = c.getInt(c.getColumnIndex("_id"));
                    break;
                case 1:
                    et_el2_1.setText(c.getString(2));
                    et_el2_2.setText(c.getString(3));
                    el2_id = c.getInt(c.getColumnIndex("_id"));
                    break;
                case 2:
                    et_el3_1.setText(c.getString(2));
                    et_el3_2.setText(c.getString(3));
                    el3_id = c.getInt(c.getColumnIndex("_id"));
                    break;
                case 3:
                    et_el4_1.setText(c.getString(2));
                    et_el4_2.setText(c.getString(3));
                    el4_id = c.getInt(c.getColumnIndex("_id"));
                    break;
            }

        }


        radioGroup.check(bundle.getInt(TODO));

        checkedRadioButton = findViewById(R.id.rb1);


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
                Long deny_Type = sp_denyTypo.getSelectedItemId();
                String readable_deny_Type = sp_denyTypo.getSelectedItem().toString();

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
                String todo_desc = checkedRadioButton.getTransitionName()+ " " + et_ai.getText().toString()+et_protocol.getText().toString();

                //PA,AI
                String ai = et_ai.getText().toString();
                String protokol = et_protocol.getText().toString();

                // подготовим данные для вставки в виде пар: наименование столбца - значение.
                // ДАТУ не обновляем

                cv.put("stage", stage);
                cv.put("state", state);
                cv.put("readable_stage", readable_stage);
                cv.put("series", series);
                cv.put("readable_deny_Type", readable_deny_Type);
                cv.put("deny_Type", deny_Type);
                cv.put("pName", pName);
                cv.put("pOboz", pOboz);
                cv.put("pNumber", pNumber);
                cv.put("pDesc", pDesc);
                cv.put("bName", bName);
                cv.put("bOboz", bOboz);
                cv.put("bNumber",bNumber);
                cv.put("bDesc", bDesc);
                cv.put("position",position);
                cv.put("positionDesc", positionDesc);
                cv.put("analysisResult",analysisResult);
                cv.put("author", author);
                cv.put("reason",reason);
                cv.put("fault", fault);
                cv.put("todo", todo);
                cv.put("todo_desc", todo_desc);
                cv.put("ai", ai);
                cv.put("protokol", protokol);

                db.update("RecordsTable", cv, "_id" + " = " + bundle.getString(_ID), null);
                Toast.makeText(this, "Данные обновлены", Toast.LENGTH_LONG).show();

                //добавляем ЭЛЕМЕНТЫ в БД

                ContentValues cv_elements = new ContentValues();

                if (!TextUtils.isEmpty(et_el1_1.getText()))
                {
                    String el_name = et_el1_1.getText().toString();
                    String el_extra = et_el1_2.getText().toString();

                    cv_elements.put("el_name", el_name);
                    cv_elements.put("el_extra", el_extra);

                    db.update("ElementsTable", cv_elements, "_id" + " = " + el1_id, null);
                }

                if (!TextUtils.isEmpty(et_el2_1.getText()))
                {
                    String el_name = et_el2_1.getText().toString();
                    String el_extra = et_el2_2.getText().toString();

                    cv_elements.put("el_name", el_name);
                    cv_elements.put("el_extra", el_extra);

                    db.update("ElementsTable", cv_elements, "_id" + " = " + el2_id, null);
                }

                if (!TextUtils.isEmpty(et_el3_1.getText()))
                {
                    String el_name = et_el3_1.getText().toString();
                    String el_extra = et_el3_2.getText().toString();

                    cv_elements.put("el_name", el_name);
                    cv_elements.put("el_extra", el_extra);

                    db.update("ElementsTable", cv_elements, "_id" + " = " + el3_id, null);
                }

                if (!TextUtils.isEmpty(et_el4_1.getText()))
                {
                    String el_name = et_el4_1.getText().toString();
                    String el_extra = et_el4_2.getText().toString();

                    cv_elements.put("el_name", el_name);
                    cv_elements.put("el_extra", el_extra);

                    db.update("ElementsTable", cv_elements, "_id" + " = " + el4_id, null);
                }


                db.close();
                Intent startRefreshedRecordsTable = new Intent(UpdateRecords.this, ShowRecords.class);
                startActivity(startRefreshedRecordsTable);
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
