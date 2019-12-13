package com.example.wordlist.DataBaseHelperDict;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String WORDS_LIST = "create table Wordlist("
            +"word text,"
            +"fy text,"
            +"sent text,"
            +"sentTrans text)";
    public Context mContext;
    public MyDatabaseHelper(Context context,String name,
                            SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WORDS_LIST);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int  oldVersion, int newVersion) {

    }
}
