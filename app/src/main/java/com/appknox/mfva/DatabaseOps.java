package com.appknox.mfva;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.appknox.mfva.TableData.TableInfo;

import static com.appknox.mfva.TableData.TableInfo.Row_id;
import static com.appknox.mfva.TableData.TableInfo.TABLE_NAME;

/**
 * Created by manikanta on 14/04/18.
 */

 public class DatabaseOps extends SQLiteOpenHelper {

    public static final int data_version = 4;

    public String CREATE_QUERY = "CREATE TABLE "+
            TABLE_NAME+ "("+ TableInfo.USER_NAME +" TEXT not null, "+TableInfo.USER_PASS+" TEXT not null, "+
            Row_id + " INTEGER PRIMARY KEY AUTOINCREMENT " + ")";

    public DatabaseOps(Context context) {


        super(context, TableData.TableInfo.DATABASE_NAME, null, data_version);
    }

    @Override

    public void onCreate(SQLiteDatabase sdb) {

        sdb.execSQL(CREATE_QUERY);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);


    }

    protected void putInformation(DatabaseOps dop, String name, String pass)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues  cv = new ContentValues();
        cv.put(TableInfo.USER_NAME,name);
        cv.put(TableInfo.USER_PASS,pass);
        SQ.insert(TABLE_NAME,null,cv);
    }
}

