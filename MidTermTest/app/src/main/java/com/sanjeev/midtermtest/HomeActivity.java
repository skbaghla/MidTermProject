package com.sanjeev.midtermtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    EditText edtusername, edtPwd;
    Button btnLogin, btnRegister;
    SwitchCompat switchButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initVariables();
        clicks();
    }

    private void clicks() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validations()) {
                    Toast.makeText(HomeActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
                    intent.putExtra("username",edtusername.getText().toString());
                    startActivity(intent);
                }
            }
        });

        switchButtons.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    btnLogin.setEnabled(true);
                    btnRegister.setEnabled(true);
                    switchButtons.setText("On");
                    btnRegister.setBackgroundResource(R.drawable.bkg_button);
                    btnLogin.setBackgroundResource(R.drawable.bkg_button);
                } else {
                    btnRegister.setBackgroundResource(R.drawable.btn_disabled);
                    btnLogin.setBackgroundResource(R.drawable.btn_disabled);
                    switchButtons.setText("Off");
                    btnLogin.setEnabled(false);
                    btnRegister.setEnabled(false);

                }
            }
        });
    }

    private boolean validations() {
        SharedPreferences sharedPref = getSharedPreferences("myPref",Context.MODE_PRIVATE);

        String currentUsername = sharedPref.getString("username", "");
        String currentPwd = sharedPref.getString("pwd", "");
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
        } else if (!(edtusername.getText().toString().equalsIgnoreCase(currentUsername))) {
            Toast.makeText(this, "Invalid username", Toast.LENGTH_SHORT).show();
            edtusername.setError("!");
            edtusername.requestFocus();
            return false;
        } else if (!(edtPwd.getText().toString().equalsIgnoreCase(currentPwd))) {
            Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
            edtPwd.setError("!");
            edtPwd.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void initVariables() {
        edtusername = findViewById(R.id.edtUsername);
        switchButtons = findViewById(R.id.switchButtons);
        edtPwd = findViewById(R.id.edtPwd);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }
}