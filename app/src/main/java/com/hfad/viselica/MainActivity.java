package com.hfad.viselica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playBtn = findViewById(R.id.playBtn);
        playBtn.setOnClickListener(this);
        Button multiBtn = findViewById(R.id.multiBtn);
        multiBtn.setOnClickListener(this);
        Button login = findViewById(R.id.login);
        login.setOnClickListener(this);
        Button records = findViewById(R.id.records);
        records.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playBtn:
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
                break;
            case R.id.multiBtn:
                Intent multiIntent =
                        new Intent(MainActivity.this, InputWordActivity.class);
                startActivity(multiIntent);
                break;
            case R.id.login:
                Intent intentLogin =
                        new Intent(MainActivity.this, SignUpSignInActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.records:
                Intent intentRecords =
                        new Intent(MainActivity.this, RecordActivity.class);
                startActivity(intentRecords);
                break;
        }
    }
}
