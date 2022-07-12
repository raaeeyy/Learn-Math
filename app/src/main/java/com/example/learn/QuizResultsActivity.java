package com.example.learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class QuizResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_quiz_results);

        final AppCompatButton startNewBtn = findViewById(R.id.startNewQuizBtn);
        final TextView correctAnswer = findViewById(R.id.correctAnswers);
        final TextView incorrectAnswers = findViewById(R.id.incorrectAnswers);

        final int getCorrectAnswers = getIntent().getIntExtra("correct", 0);
        final int getIncorrectAnswers = getIntent().getIntExtra("incorrect", 0);

        correctAnswer.setText(String.valueOf(getCorrectAnswers));
        incorrectAnswers.setText(String.valueOf(getIncorrectAnswers));

        startNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizResultsActivity.this, QuizGameActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(QuizResultsActivity.this, QuizGameActivity.class));
        finish();
    }
}