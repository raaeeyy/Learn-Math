package com.example.learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class QuizGameActivity extends AppCompatActivity {


    private String selectedTopicName ="";
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_quiz_game);

        final LinearLayout addition = findViewById(R.id.additionLayout);
        final LinearLayout multiplication = findViewById(R.id.multiplicationLayout);
        final LinearLayout subtraction = findViewById(R.id.subtractionLayout);
        final LinearLayout division = findViewById(R.id.divisionLayout);

        final AppCompatButton startBtn = findViewById(R.id.startQuizBtn);

        backBtn = (ImageView) findViewById(R.id.backBtn);

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName ="addition";

                addition.setBackgroundResource(R.drawable.white_bg_stroke);
                multiplication.setBackgroundResource(R.drawable.white_bg);
                subtraction.setBackgroundResource(R.drawable.white_bg);
                division.setBackgroundResource(R.drawable.white_bg);
            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedTopicName ="multiplication";

                multiplication.setBackgroundResource(R.drawable.white_bg_stroke);
                addition.setBackgroundResource(R.drawable.white_bg);
                subtraction.setBackgroundResource(R.drawable.white_bg);
                division.setBackgroundResource(R.drawable.white_bg);
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName ="division";

                division.setBackgroundResource(R.drawable.white_bg_stroke);
                multiplication.setBackgroundResource(R.drawable.white_bg);
                addition.setBackgroundResource(R.drawable.white_bg);
                subtraction.setBackgroundResource(R.drawable.white_bg);
            }
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName ="subtraction";

                subtraction.setBackgroundResource(R.drawable.white_bg_stroke);
                division.setBackgroundResource(R.drawable.white_bg);
                multiplication.setBackgroundResource(R.drawable.white_bg);
                addition.setBackgroundResource(R.drawable.white_bg);
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTopicName.isEmpty()){
                    Toast.makeText(QuizGameActivity.this, "Please select Topic", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(QuizGameActivity.this, QuizActivity.class);
                    intent.putExtra("selectedTopic",selectedTopicName);
                    startActivity(intent);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizGameActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}