package com.sanjeev.midtermtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edtusername, edtPwd;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        initVariables();
        clicks();
    }

    private void clicks() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validations()) {
                    Toast.makeText(RegisterActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                    savepreferences();
                    finish();

                }
            }
        });
    }

    private void savepreferences() {
        SharedPreferences sharedPref = getSharedPreferences("myPref",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString("username",edtusername.getText().toString());
        edit.putString("pwd",edtPwd.getText().toString());
        edit.apply();
    }

    private boolean validations() {
        if (edtusername.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
            edtusername.setError("!");
            edtusername.requestFocus();
            return false;
        } else if (edtPwd.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            edtPwd.setError("!");
            edtPwd.requestFocus();
            return false;
        } else if (edtPwd.getText().toString().length() < 6) {
            Toast.makeText(this, "Password should be minimum of 6 length", Toast.LENGTH_SHORT).show();
            edtPwd.setError("!");
            edtPwd.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void initVariables() {
        edtusername = findViewById(R.id.edtUsername);
        edtPwd = findViewById(R.id.edtPwd);
        btnRegister = findViewById(R.id.btnRegister);
    }
}