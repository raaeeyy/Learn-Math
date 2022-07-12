package com.example.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    MaterialButton loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginBtn = (MaterialButton) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                String n=username.getText().toString();
                if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "You successfully login "+n,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Please don't leave it empty", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}