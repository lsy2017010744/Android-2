package com.example.wordlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wordlist.DataBaseHelperDict.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "WORDLIST.db", null, 1);
        final Button addData =(Button)findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddWord.class);
                startActivity(intent);
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                //开始插入数据
//                values.put("word","abc456");
//                values.put("fy","123213");
//                values.put("sent","abcsd-32ad");
//                values.put("sentTrans","asdas32131dasd");
//                db.insert("Wordlist",null,values);
//                values.clear();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
//    /**
//     * 线程中的ui操作，用于把参数显示到屏幕上
//     *
//     * @param word
//     */
//    private void showResearchWordInterpret(final Words word) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                //在此进行ui操作
//                output.setText(word.getFy());
//                key.setText(word.getWord());
//                fy.setText(word.getFy());
//
//            }
//        });
//    }
    /**
     * 定义菜单相应事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //分别对每个菜单选项进行绑定
        Intent intent;
        switch (item.getItemId()) {
            case R.id.wordnote:
                intent = new Intent(MainActivity.this, WordnoteActivity.class);
                startActivity(intent);
                break;
            case R.id.help:
                intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
                break;
        }

        return true;
    }

//    /**
//     * 子线程中弹出toast
//     * @param string 需要显示的提示信息
//     */
//    private void showToastInThread(final String string){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(MainActivity.this,string,Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}