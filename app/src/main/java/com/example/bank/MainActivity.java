package com.example.bank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_BALANCE = 1;
    private static final String KEY_CURRENT_BALANCE = "current_balance";

    private double currentBalance = 0.00;
    private TextView balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        balance = findViewById(R.id.balance);
        Button addBalanceButton = findViewById(R.id.addBalance);
        Button payButton = findViewById(R.id.pay);
        TextView name = findViewById(R.id.nameMain);

        List<String> names = getIntent().getStringArrayListExtra("names");
        if (names != null && !names.isEmpty()) {
            name.setText(names.get(0));
        } else {
            return;
        }

        if (savedInstanceState != null) {
            currentBalance = savedInstanceState.getDouble(KEY_CURRENT_BALANCE);
        } else {
            currentBalance = getIntent().getDoubleExtra("currentBalance", 0.00);
        }
        balance.setText(String.valueOf(currentBalance));

        addBalanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityAddBalance();
            }
        });

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityPayBalance();
            }
        });
    }

    public void startActivityAddBalance() {
        Intent intent = new Intent(MainActivity.this, AddBalance.class);
        intent.putExtra("currentBalance", currentBalance);
        startActivityForResult(intent, REQUEST_CODE_ADD_BALANCE);
    }

    public void startActivityPayBalance() {
        Intent intent = new Intent(MainActivity.this, PayBalance.class);
        intent.putExtra("currentBalance", currentBalance);
        startActivityForResult(intent, REQUEST_CODE_ADD_BALANCE); // Corrigido: requestCode deve ser diferente
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_BALANCE && resultCode == RESULT_OK) {
            if (data != null) {
                currentBalance = data.getDoubleExtra("currentBalance", currentBalance);
                balance.setText(String.valueOf(currentBalance));
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(KEY_CURRENT_BALANCE, currentBalance);
    }
}
