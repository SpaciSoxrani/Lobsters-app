package com.example.lobsters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    EditText login;
    EditText password;
    Button in;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        logo = findViewById(R.id.logo);
        login = findViewById(R.id.textFieldLogin);
        password = findViewById(R.id.textFieldPassword);
        in = findViewById(R.id.containedButton);
        signUp = findViewById(R.id.textView);

        //add error for editText
    }
}