package com.hfad.viselica;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private boolean inputOrWords;
    private DataBase dataBase;
    private String[] words; //model
    private Random rand;
    private String inventedWord;
    private String userName;
    private int userCounter;
    private String currWord;
    private LinearLayout wordLayout;
    private TextView[] charViews;
    private GridView letters;
    private LetterAdapter ltrAdapt;
    private ImageView[] bodyParts;
    private final int NUMPARTS = 6;
    private int currPart;
    private int numChars;
    private int numCorr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Resources res = getResources();
        words = res.getStringArray(R.array.words);
        rand = new Random();
        wordLayout = findViewById(R.id.word);
        letters = findViewById(R.id.letters);
        userName = getIntent().getStringExtra("userName");
        bodyParts = new ImageView[NUMPARTS];
        bodyParts[0] = findViewById(R.id.head);
        bodyParts[1] = findViewById(R.id.body);
        bodyParts[2] = findViewById(R.id.arm1);
        bodyParts[3] = findViewById(R.id.arm2);
        bodyParts[4] = findViewById(R.id.leg1);
        bodyParts[5] = findViewById(R.id.leg2);
        playGame();
    }

    private void playGame() {
        inventedWord = getIntent().getStringExtra("text");

        String newWord = words[rand.nextInt(words.length)];
        if (inventedWord == null ) {
            while (newWord.equals(currWord))
                newWord = words[rand.nextInt(words.length)];
            inputOrWords = false;
            currWord = newWord;
        } else {
            inputOrWords = true;
            currWord = inventedWord.toUpperCase();
        }

        charViews = new TextView[currWord.length()];
        wordLayout.removeAllViews();
        for (int c = 0; c < currWord.length(); c++) {
            charViews[c] = new TextView(this);
            charViews[c].setText(String.format("%s", currWord.charAt(c)));

            charViews[c].setLayoutParams(new LayoutParams
                    (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            charViews[c].setGravity(Gravity.CENTER);
            charViews[c].setTextColor(Color.WHITE);
            charViews[c].setBackgroundResource(R.drawable.letter_bg);

            wordLayout.addView(charViews[c]);
        }

        ltrAdapt = new LetterAdapter(this);
        letters.setAdapter(ltrAdapt);
        currPart = 0;
        numChars = currWord.length();
        numCorr = 0;

        for (int p = 0; p < NUMPARTS; p++) {
            bodyParts[p].setVisibility(View.INVISIBLE);
        }

    }

    public void letterPressed(View view) {
        String ltr = ((TextView) view).getText().toString();
        char letterChar = ltr.charAt(0);
        view.setEnabled(false);
        view.setBackgroundResource(R.drawable.letter_down);
        boolean correct = false;
        for (int k = 0; k < currWord.length(); k++) {
            if (currWord.charAt(k) == letterChar) {
                correct = true;
                numCorr++;
                charViews[k].setTextColor(Color.BLACK);
            }
        }
        if (correct) {

            if (numCorr == numChars) {
                SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                Cursor cursor =
                        sqLiteDatabase.query(DataBase.TABLE_NAME, null, null, null, null, null, null);
                userCounter =
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(DataBase.KEY_SCORE)));
                disableBtns();

                AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
                winBuild.setTitle("Win!");
                winBuild.setMessage("Ответ:\n\n" + currWord);
                winBuild.setPositiveButton("Новая игра",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (!inputOrWords)
                                    GameActivity.this.playGame();
                                else {
                                    inventedWord = null;
                                    GameActivity.this.finish();
                                }
                            }
                        }
                );

                winBuild.setNegativeButton("Выход",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }
                );

                winBuild.show();
            }
        } else if (currPart < NUMPARTS) {

            bodyParts[currPart].setVisibility(View.VISIBLE);
            currPart++;
        } else {
            disableBtns();

            AlertDialog.Builder loseBuild = new AlertDialog.Builder(this);
            loseBuild.setTitle("Lose");
            loseBuild.setMessage("Было загадано:\n\n" + currWord);
            loseBuild.setPositiveButton("Новая игра",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (!inputOrWords)
                                GameActivity.this.playGame();
                            else {
                                inventedWord = null;
                                GameActivity.this.finish();
                            }
                        }
                    }
            );
            loseBuild.setNegativeButton("Выход",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(GameActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });

            loseBuild.show();
        }
    }

    public void disableBtns() {
        int numLetters = letters.getChildCount();
        for (int l = 0; l < numLetters; l++) {
            letters.getChildAt(l).setEnabled(false);
        }
    }
}


