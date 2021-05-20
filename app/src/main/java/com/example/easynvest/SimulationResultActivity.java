package com.example.easynvest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SimulationResultActivity extends AppCompatActivity {

    private RecyclerView simulationResultRecyclerView;
    private TextView simulationResultTextViewSimulationResult;
    private TextView simulationResultTextViewMoneyYield;
    private Button simulationResultButtonSimulateAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_result);
        findIds();
        retrieveDataFromTheMainActivity();

    }

    private void retrieveDataFromTheMainActivity() {
        Bundle dice = getIntent().getExtras();
        String investedMoney = dice.getString(Constants.INVESTED_MONEY);
        String dueData = dice.getString(Constants.DUE_DATA);
        String percentageCdi = dice.getString(Constants.PERCENTAGE_CDI);
    }

    private void findIds() {
        simulationResultButtonSimulateAgain = findViewById(R.id.simulationResultButtonSimulateAgain);
        simulationResultTextViewMoneyYield = findViewById(R.id.simulationResultTextViewMoneyYield);
        simulationResultTextViewSimulationResult = findViewById(R.id.simulationResultTextViewSimulationResult);
        simulationResultRecyclerView = findViewById(R.id.simulationResultRecyclerView);
    }
}