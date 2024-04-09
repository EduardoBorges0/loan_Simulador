package com.example.bank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PayBalance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_balance);

        EditText balancePay = findViewById(R.id.balanceValuePay);
        Button btnPay = findViewById(R.id.payEmprestimo);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String balanceStr = balancePay.getText().toString();
                if (!balanceStr.isEmpty()) {
                    double removeBalance = Double.parseDouble(balanceStr);
                    activityMainBalancePay(removeBalance);
                } else {
                    Toast.makeText(PayBalance.this, "Por favor, insira um valor válido.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void activityMainBalancePay(double payBalance) {
        double currentBalance = getIntent().getDoubleExtra("currentBalance", 0.00);
        if (currentBalance >= payBalance) {
            currentBalance -= payBalance;
            currentBalance = Math.round(currentBalance * 100.0) / 100.0;
            Intent resultIntent = new Intent();
            resultIntent.putExtra("currentBalance", currentBalance);
            setResult(RESULT_OK, resultIntent);
        } else {
            Toast.makeText(PayBalance.this, "Saldo insuficiente.", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED);
        }
        finish();
    }
}
