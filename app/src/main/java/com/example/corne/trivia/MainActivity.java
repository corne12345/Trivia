package com.example.corne.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mStartButton = (Button) findViewById(R.id.startButton);
        final RadioGroup difficultyRG = (RadioGroup) findViewById(R.id.difficulty);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameText = (EditText) findViewById(R.id.editText);
                name = (String)nameText.getText().toString();
                Log.e("Request", "test");
                int difficultyID = difficultyRG.getCheckedRadioButtonId();
                RadioButton checkedDifficulty = findViewById(difficultyID);
                String difficulty = (String) checkedDifficulty.getText();
                Intent intent = new Intent(MainActivity.this, GamePlayActivity.class);
                intent.putExtra("difficulty", difficulty);
                intent.putExtra("name", name);
                startActivity(intent);
                Log.e("Request", name);
                }
        });

        Button mHighScoreButton = (Button) findViewById(R.id.highScoresButton);
        mHighScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HighscoreActivity.class);
                startActivity(intent);
            }
        });
    }

}
