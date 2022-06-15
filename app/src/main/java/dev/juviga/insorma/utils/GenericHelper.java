package dev.juviga.insorma.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import dev.juviga.insorma.R;

public class GenericHelper implements TextWatcher {

    private final View view;
    private final TextInputLayout textInputLayout;

    public GenericHelper(View view, TextInputLayout textInputLayout) {
        this.view = view;
        this.textInputLayout = textInputLayout;
    }

    public static void toastMaker(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        final String ERR_MSG = "Field must be filled!";

        String viewText = ((EditText) view).getText().toString().trim();
        textInputLayout.setError(
                (viewText.isEmpty()) ? ERR_MSG : null
        );

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
