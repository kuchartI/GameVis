package com.hfad.viselica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        Intent a = new Intent(this, GameActivity.class);
        if (et.getText().toString().matches("")) {
            Toast.makeText(this, "Вы ввели пустую строку", Toast.LENGTH_SHORT).show();
        } else {
            a.putExtra("text", et.getText().toString());
            et.setText("");
            startActivity(a);
        }
    }
}


