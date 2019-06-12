package com.hfad.viselica;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpSignInActivity extends AppCompatActivity implements View.OnClickListener {

    DataBase dataBase;
    EditText editText;
    Button playBtn;

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


    }
}