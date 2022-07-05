package com.example.test;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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
    ImageView redx;
    TextView mismatch, invalid_email, invalid_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        btnNext = findViewById(R.id.btn_next);
        email = findViewById(R.id.email);
        enterPassword = findViewById(R.id.enter_password);
        checkPassword = findViewById(R.id.check_password);
        redx = findViewById(R.id.redx);
        mismatch = findViewById(R.id.mismatch);
        invalid_email = findViewById(R.id.invalid_email);
        invalid_password = findViewById(R.id.invalid_password);
        btnNext.setVisibility(View.INVISIBLE);
        redx.setVisibility(View.GONE);
        mismatch.setVisibility(View.GONE);
        invalid_password.setVisibility(View.GONE);
        invalid_email.setVisibility(View.GONE);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = email.getText().toString();

                Toast.makeText(getApplicationContext(),emailInput, Toast.LENGTH_SHORT).show();
                openHomePage();
            }
        });


//        email.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                validateEmail();
//                validatePassword();
//            }
//        });
//
//        enterPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                validateEmail();
//                validatePassword();
//            }
//        });

//        checkPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                confirmPassword();
//            }
//        });
        email.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (!validateEmail())
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT);
                    toast.show();
                    invalid_email.setVisibility(View.VISIBLE);
                    redx.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.INVISIBLE);
                    invalid_password.setVisibility(View.INVISIBLE);
                    mismatch.setVisibility(View.INVISIBLE);
                }
                else
                {

                    invalid_email.setVisibility(View.INVISIBLE);
                    redx.setVisibility(View.INVISIBLE);
                    if (validateEmail() && validatePassword() && confirmPassword()){
                        btnNext.setVisibility(View.VISIBLE);
                    }

                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });

        enterPassword.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (!validatePassword())
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_SHORT);
                    toast.show();
                    invalid_password.setVisibility(View.VISIBLE);
                    redx.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.INVISIBLE);
                    invalid_email.setVisibility(View.INVISIBLE);
                    mismatch.setVisibility(View.INVISIBLE);
                }
                else
                {
                    invalid_password.setVisibility(View.INVISIBLE);
                    redx.setVisibility(View.INVISIBLE);
                    if (validateEmail() && validatePassword() && confirmPassword()){
                        btnNext.setVisibility(View.VISIBLE);
                    }


                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });

        checkPassword.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (!confirmPassword())
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Mismatch Password", Toast.LENGTH_SHORT);
                    toast.show();
                    mismatch.setVisibility(View.VISIBLE);
                    redx.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.INVISIBLE);
                    invalid_email.setVisibility(View.INVISIBLE);
                    invalid_password.setVisibility(View.INVISIBLE);
                }
                else
                {
                    mismatch.setVisibility(View.INVISIBLE);
                    redx.setVisibility(View.INVISIBLE);
                    if (validateEmail() && validatePassword() && confirmPassword()){
                        btnNext.setVisibility(View.VISIBLE);
                    }

                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });


    }

    private boolean confirmPassword() {
        String pass = enterPassword.getText().toString();
        String check = checkPassword.getText().toString();

        if (checkPassword.getText().toString().trim().equals(enterPassword.getText().toString())){
            return true;
        }
        else
            return false;
    }

    private boolean validatePassword() {
        String pass = enterPassword.getText().toString();

        if (!PASSWORD_PATTERN.matcher(pass).matches()){
            return false;
        }
        else
            return true;

    }

    private boolean validateEmail() {
        String emailInput = email.getText().toString().trim();

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
            Toast toast = Toast.makeText(this, "Valid Email", Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }
    }

    private void openHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}