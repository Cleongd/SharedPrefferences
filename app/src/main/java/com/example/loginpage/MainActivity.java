package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText etUsername;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkLoginStatus()){
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
            finish();
        } else {
            findViews();
            setListeners();
        }
    }

    private void findViews(){
        etUsername = findViewById(R.id.etUsername);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void setListeners(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pass username to the next activity, then start the activity
                String username = etUsername.getText().toString();
                if(username.isEmpty()){
                    etUsername.setError("Username must not be empty!!");
                } else {
                    Userdata userdata = new Userdata();
                    userdata.setLogin(true);
                    userdata.setUsername(username);

                    Log.d("MainActivity Username", userdata.getUsername());

                    UserDataManager.saveUserData(MainActivity.this, userdata);

                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        });
    }

    private boolean checkLoginStatus(){
        Userdata userdata = UserDataManager.getUserData(this);
        return userdata.isLogin();
    }
}
