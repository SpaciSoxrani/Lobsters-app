package com.example.lobsters.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lobsters.R;
import com.example.lobsters.ui.MainActivity;
import com.example.lobsters.utils.Utils;
import com.example.lobsters.serverapi.DemoServerApi;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private LoginViewModel loginViewModel;

    private AppCompatTextView signUpButton;
    private MaterialButton signInButton;
    private TextInputLayout loginLayout;
    private TextInputEditText loginEditText;
    private TextInputLayout passwordLayout;
    private TextInputEditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginViewModel =
                ViewModelProviders.of(this).get(LoginViewModel.class);

        signUpButton = findViewById(R.id.signUpButton);
        signInButton = findViewById(R.id.signInButton);

        loginLayout = findViewById(R.id.loginLayout);
        loginEditText = findViewById(R.id.loginEditText);

        passwordLayout = findViewById(R.id.passwordLayout);
        passwordEditText = findViewById(R.id.passwordEditText);

        signUpButton.setOnClickListener(this);
        signInButton.setOnClickListener(this);

        loginViewModel.getIsLoginError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                if (isError)
                    loginLayout.setError(getString(R.string.enter_login));
                else {
                    loginLayout.setError(null);
                    loginLayout.setErrorEnabled(false);
                }
            }
        });

        loginViewModel.getIsPasswordError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                if (isError)
                    passwordLayout.setError(getString(R.string.enter_password));
                else {
                    passwordLayout.setError(null);
                    passwordLayout.setErrorEnabled(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signInButton:
                SignIn();
                break;
            case R.id.signUpButton:
                SignUp();
                break;
        }
    }

    private void SignIn() {
        String login = "";
        String password = "";

        if (loginEditText.getText() != null)
            login = loginEditText.getText().toString();
        if (passwordEditText.getText() != null)
            password = passwordEditText.getText().toString();

        loginViewModel.setIsLoginError(login.isEmpty());

        loginViewModel.setIsPasswordError(password.isEmpty());

        if (!login.isEmpty() && !password.isEmpty()) {
            boolean isSignedIn = DemoServerApi.checkSignIn(login, password);
            if (!isSignedIn) {
                Utils.ShowSimpleDialog(getString(R.string.error), getString(R.string.wrong_sign_in_credentials), this);
            } else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }

    private void SignUp() {
        Log.e("Log", "Открыть активити регистрации");
    }
}