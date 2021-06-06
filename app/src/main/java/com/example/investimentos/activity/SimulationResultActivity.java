package com.example.investimentos.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.investimentos.R;
import com.example.investimentos.adapter.SimulationInformationAdapter;
import com.example.investimentos.api.Api;
import com.example.investimentos.api.DataService;
import com.example.investimentos.domains.InvestmentResponse;
import com.example.investimentos.domains.SimulationInformation;
import com.example.investimentos.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.investimentos.utils.Utils.convertDoubleToMoney;

public class SimulationResultActivity extends AppCompatActivity {

    private RecyclerView simulationResultRecyclerView;
    private TextView simulationResultSimulationResultTextView;
    private TextView simulationMoneyYieldResultTextView;
    private Button simulationResultSimulateAgainButton;
    private ProgressBar simulationResultProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_result);
        findViewsById();
//        InvestmentRequest investmentRequest = retrieveDataFromActivity();
        setupButton();
        DataService dataService = Api.setupRetrofit().create(DataService.class);
        Call<InvestmentResponse> call = dataService.recoverInvestmentResult();
        getInvestmentResult(call);
    }

    private void getInvestmentResult(Call<InvestmentResponse> call) {
        call.enqueue(new Callback<InvestmentResponse>() {
            @Override
            public void onResponse(@NotNull Call<InvestmentResponse> call,
                                   @NotNull Response<InvestmentResponse> response) {
                simulationResultProgressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    getSuccessfullyInvestmentResult(response);
                } else {
                    createErrorDialog(getString(R.string.error_try_later));
                }
            }

            @Override
            public void onFailure(@NotNull Call<InvestmentResponse> call, @NotNull Throwable t) {
                simulationResultProgressBar.setVisibility(View.GONE);
                createErrorDialog(getString(R.string.error_connection_fail));
            }
        });
    }

    private void getSuccessfullyInvestmentResult(@NotNull Response<InvestmentResponse> response) {
        InvestmentResponse investmentResponse = response.body();
        if (investmentResponse != null) {
            showSummarySimulationResult(investmentResponse.getInvestmentParameterResponse().
                    getInvestedAmount(), investmentResponse.getGrossAmountProfit());

            List<SimulationInformation> informationList =
                    getSimulationInformation(investmentResponse);
            SimulationInformationAdapter simulationInformationAdapter =
                    new SimulationInformationAdapter(informationList);
            setupRecyclerView(simulationInformationAdapter);
        }
    }

    private void createErrorDialog(String message) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.alert_dialog_text),
                        (dialog, which) -> finish());
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showSummarySimulationResult(double investedAmount, double incomeValue) {
        simulationResultSimulationResultTextView.setText(convertDoubleToMoney(investedAmount));
        simulationMoneyYieldResultTextView.setText(convertDoubleToMoney(incomeValue));
    }

    private void setupRecyclerView(SimulationInformationAdapter simulationInformationAdapter) {
        simulationResultRecyclerView.setAdapter(simulationInformationAdapter);
    }

    private void setupButton() {
        simulationResultSimulateAgainButton.setOnClickListener(v -> finish());
    }

//    private InvestmentRequest retrieveDataFromActivity() {
//        return (InvestmentRequest) getIntent().getSerializableExtra(Constants.INVESTED_MONEY);
//    }

    private void findViewsById() {
        simulationResultSimulateAgainButton =
                findViewById(R.id.simulationResultSimulateAgainButton);
        simulationMoneyYieldResultTextView = findViewById(R.id.simulationMoneyYieldResultTextView);
        simulationResultSimulationResultTextView =
                findViewById(R.id.simulationResultSimulationResultTextView);
        simulationResultRecyclerView = findViewById(R.id.simulationResultRecyclerView);
        simulationResultProgressBar = findViewById(R.id.simulationResultProgressBar);
    }

    private List<SimulationInformation> getSimulationInformation(InvestmentResponse investmentResponse) {

        List<SimulationInformation> informationList = new ArrayList<>();

        String investedAmount =
                convertDoubleToMoney(investmentResponse.getInvestmentParameterResponse().getInvestedAmount());
        String grossAmount = convertDoubleToMoney(investmentResponse.getGrossAmount());
        String netAmount = convertDoubleToMoney(investmentResponse.getNetAmount());
        String rate =
                convertDoubleToMoney(investmentResponse.getInvestmentParameterResponse().getRate());
        String netAmountProfit = convertDoubleToMoney(investmentResponse.getNetAmountProfit());
        Date maturityDate = investmentResponse.getInvestmentParameterResponse().getMaturityDate();

        informationList.add(new SimulationInformation(getString(R.string.amount_initially_invested),
                investedAmount));
        informationList.add(new SimulationInformation(getString(R.string.gross_investment_amount)
                , grossAmount));
        informationList.add(new SimulationInformation(getString(R.string.income_value),
                netAmount));
        informationList.add(new SimulationInformation(getString(R.string.investment_income_tax),
                rate));
        informationList.add(new SimulationInformation(getString(R.string.net_investment_value),
                netAmountProfit));
        informationList.add(new SimulationInformation(getString(R.string.redemption_date),
                Utils.convertDateFormat(maturityDate)));
        return informationList;
    }
}