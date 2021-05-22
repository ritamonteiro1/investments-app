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
    private TextView simulationResultSimulationResultTextView;
    private TextView simulationMoneyYieldResultTextView;
    private Button simulationResultSimulateAgainButton;
    private static final float ERROR_FLOAT  = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_result);
        findIds();
        retrieveDataFromTheMainActivity();
        List<SimulationInformation> informationList = (List<SimulationInformation>) getSimulationInformation();
    }

    private void retrieveDataFromTheMainActivity() {
        float investedMoney = getIntent().getFloatExtra(Constants.INVESTED_MONEY, ERROR_FLOAT);
        String dueData = getIntent().getStringExtra(Constants.DUE_DATA);
        String percentageCdi = getIntent().getStringExtra(Constants.PERCENTAGE_CDI);
    }

    private void findIds() {
        simulationResultSimulateAgainButton = findViewById(R.id.simulationResultSimulateAgainButton);
        simulationMoneyYieldResultTextView = findViewById(R.id.simulationMoneyYieldResultTextView);
        simulationResultSimulationResultTextView = findViewById(R.id.simulationResultSimulationResultTextView);
        simulationResultRecyclerView = findViewById(R.id.simulationResultRecyclerView);
    }

    private SimulationInformation getSimulationInformation() {
        List<SimulationInformation> informationList = new ArrayList<>();
        return (SimulationInformation) informationList;
    }
}