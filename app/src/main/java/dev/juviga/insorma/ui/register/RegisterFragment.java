package dev.juviga.insorma.ui.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputLayout;

import dev.juviga.insorma.R;
import dev.juviga.insorma.data.model.User;
import dev.juviga.insorma.data.shared.SharedData;
import dev.juviga.insorma.ui.login.LoginFragment;
import dev.juviga.insorma.utils.GenericHelper;

public class RegisterFragment extends Fragment {

    private EditText etEmail, etUsername, etPhone, etPassword;
    private Button btnMoveLogin, btnRegister;
    private TextInputLayout tilEmail, tilUsername, tilPhone, tilPassword;
    private String email, username, phone, password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etEmail = getView().findViewById(R.id.et_email_register);
        etUsername = getView().findViewById(R.id.et_username_register);
        etPhone = getView().findViewById(R.id.et_phone_register);
        etPassword = getView().findViewById(R.id.et_password_register);
        btnRegister = getView().findViewById(R.id.btn_register);
        btnMoveLogin = getView().findViewById(R.id.btn_move_login);
        tilEmail = getView().findViewById(R.id.til_email_register);
        tilUsername = getView().findViewById(R.id.til_username_register);
        tilPhone = getView().findViewById(R.id.til_phone_register);
        tilPassword = getView().findViewById(R.id.til_password_register);

        etEmail.addTextChangedListener(new GenericHelper(etEmail, tilEmail));
        etUsername.addTextChangedListener(new GenericHelper(etUsername, tilUsername));
        etPhone.addTextChangedListener(new GenericHelper(etPhone, tilPhone));
        etPassword.addTextChangedListener(new GenericHelper(etPassword, tilPassword));

        btnRegister.setOnClickListener(view12 -> {
            email = etEmail.getText().toString();
            username = etUsername.getText().toString();
            phone = etPhone.getText().toString();
            password = etPassword.getText().toString();

            if (!email.isEmpty() && !username.isEmpty() && !phone.isEmpty() && !password.isEmpty()) {
                validate(email, username, phone, password);
            } else {
                GenericHelper.toastMaker(getContext(), "All fields must be filled!");
            }
        });

        btnMoveLogin.setOnClickListener(view1 -> {
            Fragment fragment = new LoginFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        });
    }

    private void validate(String email, String username, String phone, String password) {
        boolean isEmailOk, isUsernameOk, isPasswordOk, isUnique;

        isEmailOk = RegisterValidation.isEmailOk(email, tilEmail);
        isUsernameOk = RegisterValidation.isUsernameOk(username, tilUsername);
        isPasswordOk = RegisterValidation.isPasswordOk(password, tilPassword);
        isUnique = RegisterValidation.isUnique(username, email, tilUsername, tilEmail);

        if (isEmailOk && isUsernameOk && isPasswordOk && isUnique) {
            SharedData.USER_REPOSITORY.insert(
                    new User(0, username, email, phone, password)
            );

            Fragment fragment = new LoginFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }
}