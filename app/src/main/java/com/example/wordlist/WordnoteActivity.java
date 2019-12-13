package com.example.wordlist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordlist.DataBaseHelperDict.MyDatabaseHelper;
import com.example.wordlist.WordAdapter.WordAdapter;
import com.example.wordlist.model.Words;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class WordnoteActivity extends AppCompatActivity {

    MyDatabaseHelper db ;
    Button button;
    EditText editText;

    List<Words> words = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordnote);
        db = new MyDatabaseHelper(this,"WORDLIST.db",null,1);
        final SQLiteDatabase database = db.getWritableDatabase();

        button = (Button)findViewById(R.id.search_word);
        editText = (EditText)findViewById(R.id.input_to_search);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String str = editText.getText().toString();
                words.clear();
                Cursor cursor = database.rawQuery("select * from Wordlist where word like '%"+str+"%'", null);
                words = getCursorReturnList(cursor);
                showListToScreen(words);
            }
        });
        Cursor cursor = database.query("Wordlist", null, null, null, null, null, null);
        words = getCursorReturnList(cursor);

        showListToScreen(words);



    }



    /**
     * 传入光标得到查询结果的list集合
     * @param cursor 查询光标
     * @return
     */
    public List<Words> getCursorReturnList(Cursor cursor){
        List<Words> words = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                Words words1 = new Words();
                String word = cursor.getString(cursor.getColumnIndex("word"));
                words1.setWord(word);
                String fy = cursor.getString(cursor.getColumnIndex("fy"));
                words1.setFy(fy);
                String sent = cursor.getString(cursor.getColumnIndex("sent"));
                words1.setSent(sent);
                String sentTrans = cursor.getString(cursor.getColumnIndex("sentTrans"));
                words1.setSentTrans(sentTrans);
                words.add(words1);
            }while (cursor.moveToNext());
        }
        Collections.sort(words);
        return words;
    }

    public void showListToScreen(List<Words> words){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        WordAdapter wordAdapter = new WordAdapter(words);
        recyclerView.setAdapter(wordAdapter);
    }
}
