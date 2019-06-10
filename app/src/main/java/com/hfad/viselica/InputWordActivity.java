package com.hfad.viselica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputWordActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_word);
        et = findViewById(R.id.editText);
        Button button = findViewById(R.id.inputText);
        button.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inputText:
                String s = et.getText().toString();
                Bundle basket = new Bundle();
                basket.putString("message", s);
                Intent a = new Intent(InputWordActivity.this, GameActivity.class);
                a.putExtras(basket);
                startActivity(a);
                break;
        }
    }
}
