package com.example.easynvest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SimulationResultActivity extends AppCompatActivity {

    private RecyclerView simulationResultRecyclerView;
    private TextView simulationResultSimulationResultTextView;
    private TextView simulationMoneyYieldResultTextView;
    private Button simulationResultSimulateAgainButton;
    private InvestmentResult investmentResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_result);
        findViewsById();
        Investment investment = retrieveDataFromActivity();
        investmentResult = new InvestmentResult(investment);
        List<SimulationInformation> informationList = getSimulationInformation();
        SimuationIformationAdapter simuationIformationAdapter = new SimuationIformationAdapter(informationList);
        setupRecyclerView(simuationIformationAdapter);
        setupButton();
        showSummarySimulationResult();
    }

    private void showSummarySimulationResult() {
        simulationResultSimulationResultTextView.setText(getString(R.string.string_concatenation_simulation_result, getString(R.string.real_currency), String.valueOf(investmentResult.grossValue())));
        simulationMoneyYieldResultTextView.setText(getString(R.string.string_concatenation_simulation_result, getString(R.string.real_currency), String.valueOf(investmentResult.incomeValue())));
    }

    private void setupRecyclerView(SimuationIformationAdapter simuationIformationAdapter) {
        simulationResultRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        simulationResultRecyclerView.setAdapter(simuationIformationAdapter);
    }

    private void setupButton() {
        simulationResultSimulateAgainButton.setOnClickListener(v -> finish());
    }

    private Investment retrieveDataFromActivity() {
        return (Investment) getIntent().getSerializableExtra(Constants.INVESTED_MONEY);
    }

    private void findViewsById() {
        simulationResultSimulateAgainButton = findViewById(R.id.simulationResultSimulateAgainButton);
        simulationMoneyYieldResultTextView = findViewById(R.id.simulationMoneyYieldResultTextView);
        simulationResultSimulationResultTextView = findViewById(R.id.simulationResultSimulationResultTextView);
        simulationResultRecyclerView = findViewById(R.id.simulationResultRecyclerView);

    }

    private List<SimulationInformation> getSimulationInformation() {
        List<SimulationInformation> informationList = new ArrayList<>();
        informationList.add(new SimulationInformation(getString(R.string.amount_initially_invested), getString(R.string.string_concatenation_simulation_result, getString(R.string.real_currency),String.valueOf(investmentResult.getInvestment().getMoney()))));
        informationList.add(new SimulationInformation(getString(R.string.gross_investment_amount), getString(R.string.string_concatenation_simulation_result, getString(R.string.real_currency),String.valueOf(investmentResult.grossValue()))));
        informationList.add(new SimulationInformation(getString(R.string.income_value), getString(R.string.string_concatenation_simulation_result, getString(R.string.real_currency),String.valueOf(investmentResult.incomeValue()))));
        informationList.add(new SimulationInformation(getString(R.string.investment_income_tax), getString(R.string.string_concatenation_simulation_result, getString(R.string.real_currency),String.valueOf(investmentResult.irValue()))));
        informationList.add(new SimulationInformation(getString(R.string.net_investment_value), getString(R.string.string_concatenation_simulation_result, getString(R.string.real_currency),String.valueOf(investmentResult.netInvestmentValue()))));
        informationList.add(new SimulationInformation(getString(R.string.redemption_date), investmentResult.convertDateFormat(investmentResult.getInvestment().getDueDate())));
        return informationList;
    }
}