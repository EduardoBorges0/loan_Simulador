package com.example.bank;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import java.util.List;
import java.util.ArrayList;

public class Password extends AppCompatActivity {
    List<String> passwords = new ArrayList<>();
    List<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password2);

        EditText password = findViewById(R.id.password);
        EditText name = findViewById(R.id.name);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String passwordText = password.getText().toString();

                if (!nameText.isEmpty() && !passwordText.isEmpty()) {
                    names.add(nameText);
                    passwords.add(passwordText);

                    ActivityLogin();
                }
            }
        });
    }

    private void ActivityLogin() {
        Intent intent = new Intent(Password.this, Login.class);
        intent.putStringArrayListExtra("passwords", new ArrayList<>(passwords));
        intent.putStringArrayListExtra("names", new ArrayList<>(names));
        startActivity(intent);
        finish();
    }
}