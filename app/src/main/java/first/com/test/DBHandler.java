package first.com.test;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawan on 14-Aug-16.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 13;
    private static final String DB_NAME = "DATABASE";

    private static final String TABLE_SONGS = "SONGS";
    private static final String DELETED_NOTES = "Deleted_Notes";

    private static final String KEY_NAME = "Name";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_NOTES_TABLE = "Create Table " + TABLE_SONGS + "("
                + KEY_NAME + " Text " + ")";

        db.execSQL(CREATE_NOTES_TABLE);

        String DELETE_NOTES = "Create Table " + DELETED_NOTES + "("
                + KEY_NAME + " Text " + ")";

        db.execSQL(DELETE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        db.execSQL("DROP TABLE IF EXISTS " + DELETED_NOTES);
        onCreate(db);
    }

    public void new_note(String Name ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, Name);

        db.insert(TABLE_SONGS, null, values);
        Log.d("query",String.valueOf(TABLE_SONGS));

    }

    public List<String> access_data(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> pos_note=new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_SONGS;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            do {
                List<String> row=new ArrayList<>();
                row.add(cursor.getString(0));
                pos_note.add(String.valueOf(row));
                Log.d("rowadd2", String.valueOf(row));

            } while(cursor.moveToNext());

        }
        cursor.close();
        Log.d("note123", String.valueOf(pos_note));
        return pos_note;

    }
    public String[] get_del_single_note(int position){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] pos_note = new String[4];
        String query = "SELECT * FROM " + DELETED_NOTES;
        Cursor csr = db.rawQuery(query, null);
        csr.moveToFirst();
        int count = csr.getCount();
        int i = 0;
        while (count > 0) {
            if(i==position){
                pos_note[0] = csr.getString(0);
                pos_note[1] = csr.getString(1);
                pos_note[2] = csr.getString(2);
                pos_note[3] = csr.getString(3);
            }

            i++;
            csr.moveToNext();
            count--;
        }
        csr.close();
        db.close();
        return pos_note;
    }

    public String[] get_name() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_SONGS;
        Cursor csr = db.rawQuery(query, null);
        csr.moveToFirst();
        int count = csr.getCount();
        String [] name = new String[count];

        int i = 0;
        while (count > 0) {
            name[i] = csr.getString(0);
            i++;
            csr.moveToNext();
            count--;
        }

        csr.close();
        db.close();
        return name;
    }

    public String[] get_del_name() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + DELETED_NOTES;
        Cursor csr = db.rawQuery(query, null);
        csr.moveToFirst();
        int count = csr.getCount();
        String [] name = new String[count];

        int i = 0;
        while (count > 0) {
            name[i] = csr.getString(0);
            i++;
            csr.moveToNext();
            count--;
        }

        csr.close();
        db.close();
        return name;
    }

    public String[] get_date() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_SONGS;
        Cursor csr = db.rawQuery(query, null);
        csr.moveToFirst();
        int count = csr.getCount();
        String [] date = new String[count];

        int i = 0;
        while (count > 0) {
            date[i] = csr.getString(2);
            i++;
            csr.moveToNext();
            count--;
        }

        csr.close();
        db.close();
        return date;
    }

    public String[] get_del_date() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + DELETED_NOTES;
        Cursor csr = db.rawQuery(query, null);
        csr.moveToFirst();
        int count = csr.getCount();
        String [] date = new String[count];

        int i = 0;
        while (count > 0) {
            date[i] = csr.getString(2);
            i++;
            csr.moveToNext();
            count--;
        }

        csr.close();
        db.close();
        return date;
    }

    public void resetTable_Records() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SONGS, null, null);
    }
}

