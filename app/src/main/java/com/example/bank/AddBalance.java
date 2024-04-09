package com.example.bank;

import static java.lang.Math.round;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class AddBalance extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_balance);

            Button addBalanceButton = findViewById(R.id.payEmprestimo);
            EditText balanceEditText = findViewById(R.id.balanceValueAdd);

            addBalanceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double addedBalance = Double.parseDouble(balanceEditText.getText().toString());
                    updateBalance(addedBalance);
                }
            });
        }

        private void updateBalance(double addedBalance) {
            double currentBalance = getIntent().getDoubleExtra("currentBalance", 0.00);
            currentBalance += addedBalance ;
            double juros = currentBalance * 0.01;
            currentBalance += juros;
            currentBalance = Math.round(currentBalance * 100.0) / 100.0;
            Intent intent = new Intent();
            intent.putExtra("currentBalance", currentBalance);
            setResult(RESULT_OK, intent);
            finish();
        }
    }