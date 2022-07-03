package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");

    ImageButton btnNext;
    EditText email, enterPassword, checkPassword;
    boolean verified = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        btnNext = findViewById(R.id.btn_next);
        email = findViewById(R.id.email);
        enterPassword = findViewById(R.id.enter_password);
        checkPassword = findViewById(R.id.check_password);
        btnNext.setVisibility(View.INVISIBLE);

        validateEmail();
        validatePassword();
        confirmPassword();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });

            if (validateEmail() && validatePassword() && confirmPassword()) {
                btnNext.setVisibility(View.VISIBLE);

            }

    }

    private boolean confirmPassword() {
        if (checkPassword.getText().toString().equals(enterPassword)){
            return true;
        }
        else return false;
    }

    private boolean validatePassword() {
        String pass = enterPassword.getText().toString();

        if (pass.isEmpty()){
            Toast toast = Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(pass).matches()){
            Toast toast = Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT);
            return false;
        }
        else
            return true;

    }

    private boolean validateEmail() {
        String emailInput = email.getText().toString();

        if (emailInput.isEmpty()){
            Toast toast = Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            Toast toast = Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        else{
            return true;
        }
    }

    private void openHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}