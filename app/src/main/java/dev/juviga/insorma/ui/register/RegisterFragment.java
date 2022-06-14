package dev.juviga.insorma.ui.register;

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

import dev.juviga.insorma.R;
import dev.juviga.insorma.ui.login.LoginFragment;

public class RegisterFragment extends Fragment {

    private EditText etEmail, etUsername, etPhone, etPassword;
    private Button btnLogin, btnMoveRegister;

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
        btnLogin = getView().findViewById(R.id.btn_move_login);
        btnMoveRegister = getView().findViewById(R.id.btn_register);

        btnLogin.setOnClickListener(view1 -> {
            Fragment fragment = new LoginFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        });

        btnMoveRegister.setOnClickListener(view2 -> validate());
    }

    private void validate() {

    }
}