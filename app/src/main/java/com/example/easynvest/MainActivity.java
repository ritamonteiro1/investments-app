package com.example.easynvest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    private TextInputLayout mainMoneyTextInputLayout, mainDueDataTextInputLayout, mainCdiPercentageTextInputLayout;
    private EditText mainMoneyEditText, mainDueDataEditText, mainCdiPercentageEditText;
    private Button mainSimulateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        setupSimulateButton();
    }

    private void setupSimulateButton() {
        mainSimulateButton.setOnClickListener(v -> {
            boolean isValidMoney = isValidMoney();
            boolean isValidDate = isValidDate();
            boolean isValidPercentageCdi = isValidPercentageCdi();

            if (isValidDate && isValidMoney && isValidPercentageCdi) {
//                moveToSimulationResultActivity();
            }
        });
    }

    private boolean isValidPercentageCdi() {
        String percentageCdi = mainCdiPercentageEditText.getText().toString();
        if (percentageCdi.isEmpty()) {
            mainCdiPercentageTextInputLayout.setError(getString(R.string.enter_value_error_message));
            return false;
        } else {
            mainCdiPercentageTextInputLayout.setError(Constants.EMPTY);
            return true;
        }
    }

    private boolean isValidDate() {
        return validateDateFormat() && validateFutureDate();
    }

    private boolean validateFutureDate() {
        return true;
    }

    private boolean validateDateFormat() {
        if (checkDateFormat(mainDueDataEditText.getText().toString())) {
            mainDueDataTextInputLayout.setError(Constants.EMPTY);
            return true;
        } else {
            mainDueDataTextInputLayout.setError(getString(R.string.enter_date_format_error_message));
            return false;
        }
    }

    private Boolean checkDateFormat(String dateAsString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
            sdf.setLenient(false);
            sdf.parse(dateAsString);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    private boolean isValidMoney() {
        String money = mainMoneyEditText.getText().toString();
        if (money.isEmpty()) {
            mainMoneyTextInputLayout.setError(getString(R.string.enter_value_error_message));
            return false;
        } else {
            mainMoneyTextInputLayout.setError(Constants.EMPTY);
            return true;
        }
    }

    private void moveToSimulationResultActivity() {
        Intent intent = new Intent(this, SimulationResultActivity.class);
        intent.putExtra(Constants.INVESTED_MONEY,
                Float.parseFloat(mainMoneyEditText.getText().toString()));
        intent.putExtra(Constants.DUE_DATA, mainDueDataEditText.getText().toString());
        intent.putExtra(Constants.PERCENTAGE_CDI,
                mainCdiPercentageEditText.getText().toString());
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
