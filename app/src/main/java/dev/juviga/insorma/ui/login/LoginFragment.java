package dev.juviga.insorma.ui.login;

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

import java.util.Objects;

import dev.juviga.insorma.R;
import dev.juviga.insorma.ui.register.RegisterFragment;

public class LoginFragment extends Fragment {

    private EditText etEmail, etPassword;
    private Button btnLogin, btnMoveRegister;

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

        btnLogin.setOnClickListener(view1 -> {
            validate();
        });

        btnMoveRegister.setOnClickListener(view2 -> {
            Fragment fragment = new RegisterFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        });
    }

    private void validate() {
        String email, password;

        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
    }
}