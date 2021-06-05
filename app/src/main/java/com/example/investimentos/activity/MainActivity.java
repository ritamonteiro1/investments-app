package com.example.investimentos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.investimentos.constants.Constants;
import com.example.investimentos.domains.Investment;
import com.example.investimentos.R;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.investimentos.utils.Utils.isEmptyField;
import static com.example.investimentos.utils.Utils.parseStringToDate;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout mainMoneyTextInputLayout, mainDueDataTextInputLayout,
            mainCdiPercentageTextInputLayout;
    private EditText mainMoneyEditText, mainDueDataEditText, mainCdiPercentageEditText;
    private Button mainSimulateButton;
    private Investment investment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        setupSimulateButton();
    }

    private void setupSimulateButton() {
        mainSimulateButton.setOnClickListener(v -> {
            boolean isEmptyCdiPercentage = isEmptyField(mainCdiPercentageTextInputLayout,
                    mainMoneyEditText, this);
            boolean isEmptyDueDatePercentage = isEmptyField(mainDueDataTextInputLayout,
                    mainDueDataEditText, this);
            boolean isEmptyMoneyPercentage = isEmptyField(mainMoneyTextInputLayout,
                    mainMoneyEditText, this);

            if (isEmptyCdiPercentage || isEmptyDueDatePercentage || isEmptyMoneyPercentage)
                return;

            investment = new Investment(
                    Float.parseFloat(mainCdiPercentageEditText.getText().toString()),
                    parseStringToDate(mainDueDataEditText.getText().toString()),
                    Float.parseFloat(mainMoneyEditText.getText().toString())
            );

            validateDueDate();
        });
    }

    private void validateDueDate() {
        switch (investment.isValidDueDate()) {
            case INVALID_FORMAT:
                mainDueDataTextInputLayout.setError(getString(R.string.enter_date_format_error_message));
                break;
            case INVALID_DUE_DATE:
                mainDueDataTextInputLayout.setError(getString(R.string.enter_current_date_error_message));
                break;
            case VALID:
                moveToSimulationResultActivity();
        }
    }

    private void moveToSimulationResultActivity() {
        Intent intent = new Intent(this, SimulationResultActivity.class);
        intent.putExtra(Constants.INVESTED_MONEY, investment);
        startActivity(intent);
    }

    private void findViewsById() {
        mainSimulateButton = findViewById(R.id.mainSimulateButton);
        mainCdiPercentageEditText = findViewById(R.id.mainCdiPercentageEditText);
        mainDueDataEditText = findViewById(R.id.mainDueDataEditText);
        mainMoneyEditText = findViewById(R.id.mainMoneyEditText);
        mainMoneyTextInputLayout = findViewById(R.id.mainMoneyTextInputLayout);
        mainDueDataTextInputLayout = findViewById(R.id.mainDueDataTextInputLayout);
        mainCdiPercentageTextInputLayout = findViewById(R.id.mainCdiPercentageTextInputLayout);
    }
}
