package com.example.easynvest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mainEditTextMoney, mainEditTextDueData, mainEditTextCdiPercentage;
    private Button mainButtonSimulate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findIds();
        configureTheButtonSimulate();
    }

    private void configureTheButtonSimulate() {
        mainButtonSimulate.setOnClickListener(v -> {
            if (mainEditTextMoney.getText().toString().isEmpty()
                    || mainEditTextDueData.getText().toString().isEmpty() ||
                    mainEditTextCdiPercentage.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), getString(R.string.enter_the_value),
                        Toast.LENGTH_SHORT).show();
            } else {
                moveToSimulationResultActivity();
            }
        });
    }

    private void moveToSimulationResultActivity() {
        Intent intent = new Intent(getApplicationContext(), SimulationResultActivity.class);
        intent.putExtra(Constants.INVESTED_MONEY, mainEditTextMoney.getText().toString());
        intent.putExtra(Constants.DUE_DATA, mainEditTextDueData.getText().toString());
        intent.putExtra(Constants.PERCENTAGE_CDI, mainEditTextCdiPercentage.getText().toString());
        startActivity(intent);
    }

    private void findIds() {
        mainButtonSimulate = findViewById(R.id.mainButtonSimulate);
        mainEditTextCdiPercentage = findViewById(R.id.mainEditTextCdiPercentage);
        mainEditTextDueData = findViewById(R.id.mainEditTextDueData);
        mainEditTextMoney = findViewById(R.id.mainEditTextMoney);
    }
}