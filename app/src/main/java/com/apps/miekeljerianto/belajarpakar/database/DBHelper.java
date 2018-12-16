package com.apps.miekeljerianto.belajarpakar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.apps.miekeljerianto.belajarpakar.User;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "miekeljerianto";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "myusers";
    public static final String KEY_ID = "id";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public DBHelper(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE =" CREATE TABLE " + TABLE_NAME + " ( "
                +KEY_ID + " INTEGER PRIMARY KEY,"
                +KEY_USER_NAME + " TEXT,"
                +KEY_EMAIL + " TEXT,"
                +KEY_PASSWORD + " TEXT"
                +" ) ";
        sqLiteDatabase.execSQL( CREATE_TABLE );
        Log.d("DBHelper","Table created" +TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL( " DROP TABLE IF EXISTS " + TABLE_NAME  );
    }

    public void addUser(User user){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(  );
        contentValues.put( KEY_USER_NAME, user.userName );
        contentValues.put( KEY_EMAIL, user.email );
        contentValues.put( KEY_PASSWORD , user.password );

        long todo_id = sqLiteDatabase.insert( TABLE_NAME,null,contentValues );
    }
    public  User Authenticate(User user){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,//selecting table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},
                KEY_EMAIL + "=?" ,
                new String[]{user.email},//where clause
                null,null,null);
        if(cursor != null && cursor.moveToFirst() && cursor.getCount()>0){
            User user1 = new
                    User(cursor.getString( 0 ),
                    cursor.getString( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ));

            if(user.password.equalsIgnoreCase(user1.password)){
                return user1;
            }
        }
        return null;
    }
    public boolean isEmailExists(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query( TABLE_NAME, //selecting table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},
                KEY_EMAIL + " =? ",
                new String[]{email},
                null,null,null );
        if(cursor != null && cursor.moveToFirst() && cursor.getCount()>0){
            return true;
        }
        return false;
    }

}
