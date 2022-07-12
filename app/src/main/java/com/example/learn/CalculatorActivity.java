package com.example.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import org.mariuszgromada.math.mxparser.Expression;

public class CalculatorActivity extends AppCompatActivity {

    private EditText display;
    private ImageButton backBTN;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        backBTN = (ImageButton) findViewById(R.id.backBTN);


        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Enter in a value".equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CalculatorActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr,strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    }

    public void zeroBTN(View view){
        updateText("0");
    }

    public void oneBTN(View view){
        updateText("1");
    }

    public void twoBTN(View view){
        updateText("2");
    }

    public void threeBTN(View view){
        updateText("3");
    }

    public void fourBTN(View view){
        updateText("4");
    }

    public void fiveBTN(View view){
        updateText("5");
    }

    public void sixBTN(View view){
        updateText("6");
    }

    public void sevenBTN(View view){
        updateText("7");
    }

    public void eightBTN(View view){
        updateText("8");
    }

    public void nineBTN(View view){
        updateText("9");
    }

    public void multipleBTN(View view){
        updateText("×");
    }

    public void clearBTN(View view){
        display.setText("");
    }

    public void exponentBTN(View view){
        updateText("^");
    }

    public void divideBTN(View view){
        updateText("÷");
    }

    public void addBTN(View view){
        updateText("+");
    }

    public void subtractBTN(View view){
        updateText("-");
    }

    public void plusMinusBTN(View view){
        updateText("-");
    }

    public void pointBTN(View view){
        updateText(".");
    }

    public void equalBTN(View view){
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×","*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void backspaceBTN(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection =(SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "" );
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }
}