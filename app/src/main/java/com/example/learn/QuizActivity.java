package com.example.learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {

    private TextView questions;
    private TextView question;

    private AppCompatButton option1, option2, option3;

    private AppCompatButton nextBtn;

    private Timer quizTimer;

    private int totalTimeInMins = 1;
    private int seconds = 0;

    private List<QuestionsList> questionsList;

    private int currentQuestionPosition = 0;

    private String selectedOptionByUser = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_quiz);

        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView timer = findViewById(R.id.timer);
        final TextView selectedTopicName = findViewById(R.id.topicName);

        questions = findViewById(R.id.questions);
        question = findViewById(R.id.question);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);

        nextBtn = findViewById(R.id.nextBtn);

        final String getSelectedTopicName = getIntent().getStringExtra("selectedTopic");

        selectedTopicName.setText(getSelectedTopicName);

        questionsList = QuestionsBank.getQuestions(getSelectedTopicName);

        startTimer(timer);

        questions.setText((currentQuestionPosition+1)+"/"+questionsList.size());
        question.setText(questionsList.get(0).getQuestion());
        option1.setText(questionsList.get(0).getOption1());
        option2.setText(questionsList.get(0).getOption2());
        option3.setText(questionsList.get(0).getOption3());


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedOptionByUser.isEmpty()){

                    selectedOptionByUser = option1.getText().toString();

                    option1.setBackgroundResource(R.drawable.red_bg);
                    option1.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedOptionByUser.isEmpty()){

                    selectedOptionByUser = option2.getText().toString();

                    option2.setBackgroundResource(R.drawable.red_bg);
                    option2.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedOptionByUser.isEmpty()){

                    selectedOptionByUser = option3.getText().toString();

                    option3.setBackgroundResource(R.drawable.red_bg);
                    option3.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedOptionByUser.isEmpty()){
                    Toast.makeText(QuizActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
                else{
                    changeNextQuestion();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizTimer.purge();
                quizTimer.cancel();

                startActivity(new Intent(QuizActivity.this, QuizGameActivity.class));
                finish();
            }
        });
    }

    private void changeNextQuestion(){

        currentQuestionPosition++;

        if ((currentQuestionPosition+1)== questionsList.size()){
            nextBtn.setText("Submit Quiz");
        }

        if (currentQuestionPosition < questionsList.size()){

            selectedOptionByUser="";

            option1.setBackgroundResource(R.drawable.white_bg_stroke2);
            option1.setTextColor(Color.parseColor("#1F6BB8"));

            option2.setBackgroundResource(R.drawable.white_bg_stroke2);
            option2.setTextColor(Color.parseColor("#1F6BB8"));

            option3.setBackgroundResource(R.drawable.white_bg_stroke2);
            option3.setTextColor(Color.parseColor("#1F6BB8"));

            questions.setText((currentQuestionPosition+1)+"/"+questionsList.size());
            question.setText(questionsList.get(currentQuestionPosition).getQuestion());
            option1.setText(questionsList.get(currentQuestionPosition).getOption1());
            option2.setText(questionsList.get(currentQuestionPosition).getOption2());
            option3.setText(questionsList.get(currentQuestionPosition).getOption3());
        }
        else{
            Intent intent = new Intent(QuizActivity.this, QuizResultsActivity.class);
            intent.putExtra("correct", getCorrectAnswer());
            intent.putExtra("incorrect",getInCorrectAnswer());
            startActivity(intent);

            finish();
        }
    }

    private void startTimer(TextView timerTextView){
        quizTimer = new Timer();

        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(seconds == 0){
                    totalTimeInMins--;
                    seconds = 59;
                }
                else if(seconds == 0 && totalTimeInMins == 0){

                    quizTimer.purge();
                    quizTimer.cancel();

                    Toast.makeText(QuizActivity.this, "Time is Over", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(QuizActivity.this,QuizResultsActivity.class);
                    intent.putExtra("correct", getCorrectAnswer());
                    intent.putExtra("incorrect", getInCorrectAnswer());
                    startActivity(intent);

                    finish();
                }
                else{
                    seconds--;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String finalMinutes = String.valueOf(totalTimeInMins);
                        String finalSeconds = String.valueOf(seconds);

                        if(finalMinutes.length() == 1){
                            finalMinutes = "0"+finalMinutes;
                        }

                        if (finalSeconds.length() == 1){
                            finalSeconds = "0"+finalSeconds;
                        }

                        timerTextView.setText(finalMinutes +":"+finalSeconds);
                    }
                });
            }
        }, 1000, 1000);
    }
    private  int getCorrectAnswer(){

        int correctAnswers = 0;

        for(int i=0;i<questionsList.size();i++){

            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsList.get(i).getAnswer();

            if (getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }

        return correctAnswers;

    }

    private  int getInCorrectAnswer(){

        int correctAnswers = 0;

        for(int i=0;i<questionsList.size();i++){

            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsList.get(i).getAnswer();

            if (!getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }

        return correctAnswers;

    }

    @Override
    public void onBackPressed() {

        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(QuizActivity.this, QuizGameActivity.class));
        finish();
    }

    private void revealAnswer(){

        final String getAnswer = questionsList.get(currentQuestionPosition).getAnswer();

        if (option1.getText().toString().equals(getAnswer)){
            option1.setBackgroundResource(R.drawable.green_bg10);
            option1.setTextColor(Color.WHITE);
        }
        else if (option2.getText().toString().equals(getAnswer)){
            option2.setBackgroundResource(R.drawable.green_bg10);
            option2.setTextColor(Color.WHITE);
        }
        else if (option3.getText().toString().equals(getAnswer)){
            option3.setBackgroundResource(R.drawable.green_bg10);
            option3.setTextColor(Color.WHITE);
        }
    }
}