package com.example.investimentos.utils;

import android.content.Context;
import android.widget.EditText;

import com.example.investimentos.constants.Constants;
import com.example.investimentos.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.investimentos.constants.Constants.DATE_FORMAT;

public class Utils {
    private static final NumberFormat numberFormat =
            NumberFormat.getCurrencyInstance(Constants.LOCALE_BR);

    public static Date parseStringToDate(String value) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
            sdf.setLenient(false);
            return sdf.parse(value);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static boolean isEmptyField(TextInputLayout textInputLayout, EditText editText,
                                       Context context) {
        String value = editText.getText().toString();
        if (value.isEmpty()) {
            textInputLayout.setError(context.getString(R.string.enter_value_error_message));
            return true;
        } else {
            textInputLayout.setError(Constants.EMPTY);
            return false;
        }
    }

    public static String convertDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT, Constants.LOCALE_BR);
        return dateFormat.format(date);
    }

    public static String convertFloatToMoney(float value) {
        return numberFormat.format(value);
    }
}
