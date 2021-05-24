package com.example.easynvest;

import android.content.Context;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.easynvest.Constants.DATE_FORMAT;

public class Utils {
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
}
