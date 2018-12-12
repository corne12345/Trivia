package com.example.corne.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GamePlayActivity extends AppCompatActivity implements TriviaRequest.Callback {

    private Button mButtonA;
    private Button mButtonB;
    private Button mButtonC;
    private Button mButtonD;
    private Button mButtonNext;
    private TextView mQuestionTextView;
    private ArrayList<Question> mQuestionBank;
    private int AMOUNT_OF_QUESTIONS = 50;

    private int mCurrentIndex = 0;
    private int score;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        name = (String) getIntent().getSerializableExtra("name");
        String difficulty = (String) getIntent().getSerializableExtra("difficulty");

        TriviaRequest x = new TriviaRequest(this, difficulty);
        x.getQuestions(this);
//        updateQuestion();
    }

    @Override
    public void gotQuestions(ArrayList<Question> questionList) {
//        Toast.makeText(this, mQuestionBank.get(0).getAnswerA(), Toast.LENGTH_LONG).show();
        mQuestionBank = questionList;

        // Set onclicklisteners for all the buttons
            mButtonA = (Button) findViewById(R.id.buttonA);
            mButtonA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = mQuestionBank.get(mCurrentIndex).getAnswerA();
                    checkAnswer(answer);
                }
            });


            mButtonB = (Button) findViewById(R.id.buttonB);
            mButtonB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = mQuestionBank.get(mCurrentIndex).getAnswerB();
                    checkAnswer(answer);
                }
            });

            mButtonC = (Button) findViewById(R.id.buttonC);
            mButtonC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = mQuestionBank.get(mCurrentIndex).getAnswerC();
                    checkAnswer(answer);
                }
            });

            mButtonD = (Button) findViewById(R.id.buttonD);
            mButtonD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = mQuestionBank.get(mCurrentIndex).getAnswerD();
                    checkAnswer(answer);
                }
            });
            mButtonNext = (Button) findViewById(R.id.nextButton);
            mButtonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCurrentIndex = (mCurrentIndex + 1) % AMOUNT_OF_QUESTIONS;
                    updateQuestion();
                }
            });
        }
    @Override
    public void gotQuestonsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        mQuestionTextView = (TextView) findViewById(R.id.questionText);
        String question = mQuestionBank.get(mCurrentIndex).getQuestion();
        mQuestionTextView.setText(question);

        mButtonA = (Button) findViewById(R.id.buttonA);
        String answerA = mQuestionBank.get(mCurrentIndex).getAnswerA();
        mButtonA.setText(answerA);
        mButtonA.setEnabled(true);

        mButtonB = (Button) findViewById(R.id.buttonB);
        String answerB = mQuestionBank.get(mCurrentIndex).getAnswerB();
        mButtonB.setText(answerB);
        mButtonB.setEnabled(true);

        mButtonC = (Button) findViewById(R.id.buttonC);
        String answerC = mQuestionBank.get(mCurrentIndex).getAnswerC();
        mButtonC.setText(answerC);
        mButtonC.setEnabled(true);

        mButtonD = (Button) findViewById(R.id.buttonD);
        String answerD = mQuestionBank.get(mCurrentIndex).getAnswerD();
        mButtonD.setText(answerD);
        mButtonD.setEnabled(true);

        if (mCurrentIndex == AMOUNT_OF_QUESTIONS - 1){
            Intent intent = new Intent(GamePlayActivity.this, FinalActivity.class);
            intent.putExtra("score", Integer.toString(score));
            intent.putExtra("name", name);
            startActivity(intent);
        }
    }

    private void checkAnswer(String checkedAnswer) {
        int messageResId;
        String correctAnswer = mQuestionBank.get(mCurrentIndex).getCorrectAnswer();

        if (checkedAnswer.equals(correctAnswer)) {
            messageResId = R.string.correct_toast;
            score += 1;
            String scoreStringNum = Integer.toString(score);
            TextView scoreView = findViewById(R.id.scoreView);
            String scoreString = "Points: " + scoreStringNum;
            scoreView.setText(scoreString);
        } else {
            messageResId = R.string.incorrect_toast;
            score -= 1;
            String scoreStringNum = Integer.toString(score);
            TextView scoreView = findViewById(R.id.scoreView);
            String scoreString = "Points: " + scoreStringNum;
            scoreView.setText(scoreString);
        }
        mButtonA.setEnabled(false);
        mButtonB.setEnabled(false);
        mButtonC.setEnabled(false);
        mButtonD.setEnabled(false);

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}

