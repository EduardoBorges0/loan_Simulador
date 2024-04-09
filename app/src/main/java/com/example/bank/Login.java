package com.example.bank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;



public class Login extends AppCompatActivity {
    private EditText passwordLogin;
    private Button btnLogin;
    private List<String> passwords, names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passwordLogin = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.btnLogin);

        passwords = getIntent().getStringArrayListExtra("passwords");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordText = passwordLogin.getText().toString();
                if (!passwordText.isEmpty()) {
                    if (passwords.contains(passwordText)) {
                        ActivityMain();
                    } else {
                        Toast.makeText(Login.this, "Senha inválida", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void ActivityMain() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        names = getIntent().getStringArrayListExtra("names");
        intent.putStringArrayListExtra("names", new ArrayList<>(names));
        startActivity(intent);
        finish();
    }
}
