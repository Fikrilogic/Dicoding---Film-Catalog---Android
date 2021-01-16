package com.android.uas1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.security.keystore.UserNotAuthenticatedException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usernameText, passwordText;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameText = findViewById(R.id.usernameBox);
        passwordText = findViewById(R.id.passwordbox);
        btnLogin = findViewById(R.id.buttonLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernameText.getText().toString().equals("Admin")&&passwordText.getText()
                .toString().equals("123456")){
                    Toast.makeText(MainActivity.this, "Login Sukses", Toast.LENGTH_SHORT).show();
                    Intent move = new Intent(MainActivity.this, DashboardApp.class);
                    startActivity(move);
                } else{
                    Toast.makeText(MainActivity.this, "Username/Password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}