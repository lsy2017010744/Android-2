package com.example.wordlist;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.wordlist.DataBaseHelperDict.MyDatabaseHelper;
public class DatabaseProvider extends ContentProvider {
    public DatabaseProvider() {
    }
    public static final int word_dir=0;
    public static final int word_item=1;
    public static final String authority="com.example.wordlist.provider";
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper database;
    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(authority,"Wordlist",word_dir);
        uriMatcher.addURI(authority,"Wordlist/#",word_item);
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        SQLiteDatabase db=database.getWritableDatabase();
        int deleteRows=0;
        switch (uriMatcher.match(uri)){
            case word_dir:
                deleteRows=db.delete("Wordlist",selection,selectionArgs);
                break;
            case word_item:
                String wordName=uri.getPathSegments().get(1);
                deleteRows=db.delete("Wordlist","word=?",new String[]{wordName});
                break;
            default:
                break;
        }
        return deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)){
            case word_dir:
                return "vnd.android.cursor.dir/vnd.com.example.wordlist.provider.Wordlist";
            case word_item:
                return "vnd.android.cursor.item/vnd.com.example.wordlist.provider.Wordlist";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        SQLiteDatabase db=database.getWritableDatabase();
        Uri urireturn=null;
        switch (uriMatcher.match(uri)){
            case word_dir:
            case word_item:
                long newWordName=db.insert("Wordlist",null,values);
                urireturn=Uri.parse("content://"+authority+"/Wordlist/"+newWordName);
                break;
            default:
                break;
        }
        return urireturn;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        database=new MyDatabaseHelper(getContext(),"WORDLIST.db",null,1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteDatabase db=database.getReadableDatabase();
        Cursor cursor=null;
        switch (uriMatcher.match(uri)){
            case word_dir:
                cursor=db.query("Wordlist",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case word_item:
                String wordName=uri.getPathSegments().get(1);
                cursor=db.query("Wordlist",projection,"word=?",new String[]{wordName},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        SQLiteDatabase db=database.getWritableDatabase();
        int updateRows=0;
        switch (uriMatcher.match(uri)){
            case word_dir:
                updateRows=db.update("Wordlist",values,selection,selectionArgs);
                break;
            case word_item:
                String wordName=uri.getPathSegments().get(1);
                updateRows=db.update("Wordlist",values,"word=?",new String[]{wordName});
                break;
            default:
                break;
        }
        return updateRows;
    }
}
