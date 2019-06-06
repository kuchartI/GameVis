package com.hfad.viselica;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private String[] words;
    private Random rand;
    private String currWord;
    private LinearLayout wordLayout;
    private TextView[] charViews;
    private GridView letters;
    private LetterAdapter ltrAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Resources res = getResources();
        words = res.getStringArray(R.array.words);
        rand = new Random();
        currWord = "";
        wordLayout = findViewById(R.id.word);
        letters = findViewById(R.id.letters);
        playGame();
    }

    private void playGame() {
        String newWord = words[rand.nextInt(words.length)];

        while (newWord.equals(currWord))
            newWord = words[rand.nextInt(words.length)];
        currWord = newWord;

        charViews = new TextView[currWord.length()];
        wordLayout.removeAllViews();
        for (int c = 0; c < currWord.length(); c++) {
            charViews[c] = new TextView(this);
            charViews[c].setText("" + currWord.charAt(c));

            charViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            charViews[c].setGravity(Gravity.CENTER);
            charViews[c].setTextColor(Color.WHITE);
            charViews[c].setBackgroundResource(R.drawable.letter_bg);
            //add to layout
            wordLayout.addView(charViews[c]);
        }
        ltrAdapt = new LetterAdapter(this);
        letters.setAdapter(ltrAdapt);
    }
}
