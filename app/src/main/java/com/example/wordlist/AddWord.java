package com.example.wordlist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wordlist.DataBaseHelperDict.MyDatabaseHelper;
import com.example.wordlist.model.Words;

public class AddWord extends AppCompatActivity {
    TextView key;
    EditText fy;
    EditText word_orig;
    EditText word_trans;
    Button add_word;
    MyDatabaseHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        final Words wordValue = (Words) getIntent().getSerializableExtra("bean");
        dbhelper = new MyDatabaseHelper(this,"WORDLIST.db",null,1);
        final SQLiteDatabase db = dbhelper.getWritableDatabase();
        //获取控件
        key = (TextView) findViewById(R.id.key);
        fy = (EditText) findViewById(R.id.fy);
        word_orig = (EditText) findViewById(R.id.word_orig);
        word_trans = (EditText) findViewById(R.id.word_trans);
        add_word = (Button)findViewById(R.id.add_word);
//        为各个textview设置文本
        key.setText(null);
        fy.setText(null);
        word_orig.setText(null);
        word_trans.setText(null);
        //添加事件
        add_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();
                values.put("word",key.getText().toString());
                values.put("fy",fy.getText().toString());
                values.put("sent",word_orig.getText().toString());
                values.put("sentTrans",word_trans.getText().toString());
                int i = (int) db.insert("Wordlist",null,values);
                if (i>0){
                    Toast.makeText(AddWord.this,"添加成功",Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(AddWord.this,"添加失败",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddWord.this,WordnoteActivity.class);
                startActivity(intent);
                finish();

            }
        });



    }
}
