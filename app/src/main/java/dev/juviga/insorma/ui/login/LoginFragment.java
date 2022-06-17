package dev.juviga.insorma.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import dev.juviga.insorma.R;
import dev.juviga.insorma.data.model.User;
import dev.juviga.insorma.data.shared.SharedData;
import dev.juviga.insorma.ui.LandingActivity;
import dev.juviga.insorma.ui.MainActivity;
import dev.juviga.insorma.ui.register.RegisterFragment;
import dev.juviga.insorma.utils.GenericHelper;

public class LoginFragment extends Fragment {

    private EditText etEmail, etPassword;
    private Button btnLogin, btnMoveRegister;
    private TextInputLayout tilEmail, tilPassword;
    private String email, password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etEmail = getView().findViewById(R.id.et_email_login);
        etPassword = getView().findViewById(R.id.et_password_login);
        btnLogin = getView().findViewById(R.id.btn_login);
        btnMoveRegister = getView().findViewById(R.id.btn_move_register);
        tilEmail = getView().findViewById(R.id.til_email_login);
        tilPassword = getView().findViewById(R.id.til_password_login);

        etEmail.addTextChangedListener(new GenericHelper(etEmail, tilEmail));
        etPassword.addTextChangedListener(new GenericHelper(etPassword, tilPassword));

        btnLogin.setOnClickListener(view1 -> {
            email = etEmail.getText().toString();
            password = etPassword.getText().toString();

            if (!email.isEmpty() && !password.isEmpty()) {
                validate(email, password);
            } else {
                GenericHelper.toastMaker(getContext(), "All fields must be filled!");
            }
        });

        btnMoveRegister.setOnClickListener(view2 -> {
            Fragment fragment = new RegisterFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        });
    }

    private void validate(String email, String password) {
        User user = SharedData.USER_REPOSITORY.findByEmail(email);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                SharedPreferences sp = getActivity().getSharedPreferences("LOGGED_IN_USER", Context.MODE_PRIVATE);
                SharedPreferences.Editor spEdit = sp.edit();

                spEdit.putString("username", user.getUsername())
                        .putString("email", user.getEmailAddress())
                        .putString("phone", user.getPhoneNumber())
                        .putString("password", user.getPassword())
                        .apply();

                startActivity(new Intent(getActivity(), MainActivity.class));
            } else {
                tilPassword.setError("Password does not match!");
            }
        } else {
            tilEmail.setError("Email not found!");
        }
    }
}