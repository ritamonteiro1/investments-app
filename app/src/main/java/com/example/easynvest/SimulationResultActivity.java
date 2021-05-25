package com.example.easynvest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SimulationResultActivity extends AppCompatActivity {

    private RecyclerView simulationResultRecyclerView;
    private TextView simulationResultSimulationResultTextView;
    private TextView simulationMoneyYieldResultTextView;
    private Button simulationResultSimulateAgainButton;
    private InvestmentResult investmentResult;
    private String amountInitiallyInvested, grossInvestmentAmount, incomeValue,
            investmentIncomeTax, netInvestmentValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_result);
        findViewsById();
        Investment investment = retrieveDataFromActivity();
        investmentResult = new InvestmentResult(investment);
        List<SimulationInformation> informationList = getSimulationInformation();
        SimuationIformationAdapter simuationIformationAdapter =
                new SimuationIformationAdapter(informationList);
        setupRecyclerView(simuationIformationAdapter);
        setupButton();
        showSummarySimulationResult();
    }

    private void showSummarySimulationResult() {
        simulationResultSimulationResultTextView.setText(investmentResult.convertFloatToMoney(investmentResult.grossValue()));
        simulationMoneyYieldResultTextView.setText(investmentResult.convertFloatToMoney(investmentResult.incomeValue()));
    }

    private void setupRecyclerView(SimuationIformationAdapter simuationIformationAdapter) {
        simulationResultRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayout.VERTICAL));
        simulationResultRecyclerView.setAdapter(simuationIformationAdapter);
    }

    private void setupButton() {
        simulationResultSimulateAgainButton.setOnClickListener(v -> finish());
    }

    private Investment retrieveDataFromActivity() {
        return (Investment) getIntent().getSerializableExtra(Constants.INVESTED_MONEY);
    }

    private void findViewsById() {
        simulationResultSimulateAgainButton =
                findViewById(R.id.simulationResultSimulateAgainButton);
        simulationMoneyYieldResultTextView = findViewById(R.id.simulationMoneyYieldResultTextView);
        simulationResultSimulationResultTextView =
                findViewById(R.id.simulationResultSimulationResultTextView);
        simulationResultRecyclerView = findViewById(R.id.simulationResultRecyclerView);

    }

    private List<SimulationInformation> getSimulationInformation() {
        findSimulationResultValues();

        List<SimulationInformation> informationList = new ArrayList<>();
        informationList.add(new SimulationInformation(getString(R.string.amount_initially_invested),
                amountInitiallyInvested));
        informationList.add(new SimulationInformation(getString(R.string.gross_investment_amount)
                , grossInvestmentAmount));
        informationList.add(new SimulationInformation(getString(R.string.income_value),
                incomeValue));
        informationList.add(new SimulationInformation(getString(R.string.investment_income_tax),
                investmentIncomeTax));
        informationList.add(new SimulationInformation(getString(R.string.net_investment_value),
                netInvestmentValue));
        informationList.add(new SimulationInformation(getString(R.string.redemption_date),
                investmentResult.convertDateFormat(investmentResult.getInvestment().getDueDate())));
        return informationList;
    }

    private void findSimulationResultValues() {
        amountInitiallyInvested = investmentResult.convertFloatToMoney(investmentResult.getInvestment().getMoney());
        grossInvestmentAmount = investmentResult.convertFloatToMoney(investmentResult.grossValue());
        incomeValue = investmentResult.convertFloatToMoney(investmentResult.incomeValue());
        investmentIncomeTax = investmentResult.convertFloatToMoney(investmentResult.irValue());
        netInvestmentValue = investmentResult.convertFloatToMoney(investmentResult.netInvestmentValue());
    }
}