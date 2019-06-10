package com.hfad.viselica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
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
        et.setFilters(new InputFilter[] {
                new InputFilter() {
                    public CharSequence filter(CharSequence src, int start,
                                               int end, Spanned dst, int dstart, int dend) {
                        if(src.equals("")){
                            return src;
                        }
                        if(src.toString().matches("[а-яА-Я]+")){
                            return src;
                        }
                        return "";
                    }
                }
        });
        Button button = findViewById(R.id.inputText);
        button.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent a = new Intent(this, GameActivity.class);
        a.putExtra("text", et.getText().toString());
        et.setText("");
        startActivity(a);

    }
}

