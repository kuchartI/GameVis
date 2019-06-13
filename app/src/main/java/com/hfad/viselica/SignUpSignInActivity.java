package com.hfad.viselica;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpSignInActivity extends AppCompatActivity implements View.OnClickListener {

     private DataBase dataBase;
    EditText editText;
    Button playBtn;
    Boolean firstinBase = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_sign_in);
        playBtn = findViewById(R.id.inputLogin);
        playBtn.setOnClickListener(this);
        editText = findViewById(R.id.editLogin);
        dataBase = new DataBase(this);
    }

    @Override
    public void onClick(View v) {
        String login = editText.getText().toString();

        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        switch (v.getId()) {
            case R.id.inputLogin:
                Cursor cursor =
                        sqLiteDatabase.query(DataBase.TABLE_NAME, null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        Log.d("wLog", "ID= " + cursor.getInt(cursor.getColumnIndex(DataBase.KEY_ID))
                                + " s " + cursor.getString(cursor.getColumnIndex(DataBase.KEY_NAME))
                                + " SCORE " + cursor.getString(cursor.getColumnIndex(DataBase.KEY_SCORE)) +
                                cursor.getCount());
                        if (cursor.getString(cursor.getColumnIndex(DataBase.KEY_NAME)).equals(login)) {
                            Intent intent = new Intent(this, GameActivity.class);
                            firstinBase = false;
                            intent.putExtra("index",cursor.getString(cursor.getColumnIndex(DataBase.KEY_ID)) );
                            intent.putExtra("userName", login);
                            editText.setText("");
                            startActivity(intent);
                        }
                    } while (cursor.moveToNext());
                    if (firstinBase) {
                        contentValues.put(DataBase.KEY_NAME, login);
                        contentValues.put(DataBase.KEY_SCORE, "0");
                        sqLiteDatabase.insert(DataBase.TABLE_NAME, null, contentValues);
                    }
                } else {
                    contentValues.put(DataBase.KEY_NAME, login);
                    contentValues.put(DataBase.KEY_SCORE, "0");
                    sqLiteDatabase.insert(DataBase.TABLE_NAME, null, contentValues);
                }
                firstinBase = true;
                cursor.close();
        }
        dataBase.close();
    }
    // sqLiteDatabase.delete(DataBase.TABLE_NAME, null, null);
}





