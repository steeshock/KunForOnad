package ru.steeshock.kunforonad;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


/**
 * Created by steeshock on 01.04.2018.
 */

public class ShowElements extends AppCompatActivity {


    private static final int CM_UPDATE_ID = 1;
    private static final int CM_DELETE_ID = 2;


    String[] from = new String[] {"_id", "el_name", "el_extra", "record_id"};
    int[] to = new int[] { R.id.tvID, R.id.tvName, R.id.tvExtra, R.id.tvRecordID };


    TextView tv_summary;

    DBHelper dbHelper = new DBHelper (this);
    ListView list;
    SimpleCursorAdapter scAdapter;
    SQLiteDatabase db;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_elements);

        db = dbHelper.getWritableDatabase();
        c = db.query("ElementsTable", null, null, null, null, null, null);

        tv_summary = findViewById(R.id.tv_summary);
        tv_summary.setText(String.valueOf(c.getCount()));


        scAdapter = new SimpleCursorAdapter(this, R.layout.item_element, c, from, to,0);
        list = findViewById(R.id.list);
        list.setAdapter(scAdapter);

        registerForContextMenu(list);
    }

        public void onCreateContextMenu(ContextMenu menu, View v,
                                        ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            menu.add(0, CM_DELETE_ID, 0, R.string.delete_record);
            menu.add(0, CM_UPDATE_ID, 0, R.string.update_record);
        }

        public boolean onContextItemSelected(MenuItem item) {


            // получаем из пункта контекстного меню данные по пункту списка
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

           switch (item.getItemId()) {

               case CM_DELETE_ID:

                   // извлекаем id записи и удаляем соответствующую запись в БД
                   db.delete("RecordsTable", "_id" + " = " + acmi.id, null);



                   c = db.query("RecordsTable", null, null, null, null, null, null);
                   scAdapter = new SimpleCursorAdapter(this, R.layout.item_record, c, from, to, 0);
                   list.setAdapter(scAdapter);
                   tv_summary.setText(String.valueOf(c.getCount()));

                   return true;

               case CM_UPDATE_ID:

                   Bundle record = new Bundle();

                   record.putString(UpdateRecords._ID,c.getString(c.getColumnIndex("_id")));
                   record.putLong(UpdateRecords.DATE, c.getLong(c.getColumnIndex("date")));
                   record.putLong(UpdateRecords.STAGE, c.getLong(c.getColumnIndex("stage")));
                   record.putString(UpdateRecords.STATE, c.getString(c.getColumnIndex("state")));
                   record.putString(UpdateRecords.SERIES, c.getString(c.getColumnIndex("series")));
                   record.putString(UpdateRecords.P_NAME, c.getString(c.getColumnIndex("pName")));
                   record.putString(UpdateRecords.P_OBOZ, c.getString(c.getColumnIndex("pOboz")));
                   record.putString(UpdateRecords.P_NUMBER, c.getString(c.getColumnIndex("pNumber")));
                   record.putString(UpdateRecords.P_DESC, c.getString(c.getColumnIndex("pDesc")));
                   record.putString(UpdateRecords.B_NAME, c.getString(c.getColumnIndex("bName")));
                   record.putString(UpdateRecords.B_OBOZ, c.getString(c.getColumnIndex("bOboz")));
                   record.putString(UpdateRecords.B_NUMBER, c.getString(c.getColumnIndex("bNumber")));
                   record.putString(UpdateRecords.B_DESC, c.getString(c.getColumnIndex("bDesc")));
                   record.putLong(UpdateRecords.DENY_TYPO, c.getLong(c.getColumnIndex("deny_Type")));
                   record.putString(UpdateRecords.POSITION, c.getString(c.getColumnIndex("position")));
                   record.putString(UpdateRecords.POSITION_DESC, c.getString(c.getColumnIndex("positionDesc")));
                   record.putString(UpdateRecords.ANALYSIS_RESULT, c.getString(c.getColumnIndex("analysisResult")));
                   record.putString(UpdateRecords.AUTHOR, c.getString(c.getColumnIndex("author")));
                   record.putString(UpdateRecords.REASON, c.getString(c.getColumnIndex("reason")));
                   record.putString(UpdateRecords.FAULT, c.getString(c.getColumnIndex("fault")));
                   record.putInt(UpdateRecords.TODO, c.getInt(c.getColumnIndex("todo")));
                   record.putString(UpdateRecords.TODO_DESC, c.getString(c.getColumnIndex("todo_desc")));
                   record.putString(UpdateRecords.AI, c.getString(c.getColumnIndex("ai")));
                   record.putString(UpdateRecords.PROTOKOL, c.getString(c.getColumnIndex("protokol")));

                   Intent openUpdateRecords = new Intent(ShowElements.this, UpdateRecords.class);
                   openUpdateRecords.putExtras(record);
                   startActivity (openUpdateRecords);
                   finish();
           }
            return super.onContextItemSelected(item);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_show_records, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.new_item:
                Intent openCreateNewRecord = new Intent(ShowElements.this, MainActivity.class);
                startActivity (openCreateNewRecord);
                finish();
                break;

            case R.id.update_item:

                c = db.query("RecordsTable", null, null, null, null, null, null);
                scAdapter = new SimpleCursorAdapter(this, R.layout.item_record, c, from, to, 0);
                list.setAdapter(scAdapter);

            default: break;
        }
        return super.onOptionsItemSelected(item);
    }




    }
