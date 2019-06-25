package com.hfad.viselica;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        DataBase dataBase = new DataBase(this);
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        // Зададим условие для выборки - список столбцов
        String[] projection = {
                DataBase.KEY_ID,
                DataBase.KEY_NAME,
                DataBase.KEY_SCORE};

        // Делаем запрос
        Cursor cursor = sqLiteDatabase.query(
                DataBase.TABLE_NAME,   // таблица
                projection,            // столбцы
                null,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // порядок сортировки

        TextView displayTextView = findViewById(R.id.text_view_info);

        try {
            displayTextView.append(DataBase.KEY_ID + " - " +
                    DataBase.KEY_NAME + " - " +
                    DataBase.KEY_SCORE + "\n");

            // Узнаем индекс каждого столбца
            int idColumnIndex = cursor.getColumnIndex(DataBase.KEY_ID);
            int nameColumnIndex = cursor.getColumnIndex(DataBase.KEY_NAME);
            int scoreColumnIndex = cursor.getColumnIndex(DataBase.KEY_SCORE);


            // Проходим через все ряды
            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentScore = cursor.getString(scoreColumnIndex);
                // Выводим значения каждого столбца
                displayTextView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentScore));
            }
        } finally {
            cursor.close();
        }
}}