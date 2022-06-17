package dev.juviga.insorma.ui.profile;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import dev.juviga.insorma.R;
import dev.juviga.insorma.data.model.User;
import dev.juviga.insorma.data.repository.UserRepository;
import dev.juviga.insorma.data.shared.SharedData;
import dev.juviga.insorma.utils.GenericHelper;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private MaterialToolbar toolbar;
    private TextView tvEmail, tvUsername, tvPhone;
    private EditText etEditUsername;
    private Button btnEdit, btnSave, btnDelete, btnLogout;
    private String loggedInUsername, loggedInEmail, loggedInPhone, loggedInPassword;
    private SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sp = this.getActivity().getSharedPreferences("LOGGED_IN_USER", MODE_PRIVATE);

        loggedInUsername = sp.getString("username", "");
        loggedInEmail = sp.getString("email", "");
        loggedInPhone = sp.getString("phone", "");
        loggedInPassword = sp.getString("password", "");

        toolbar = getView().findViewById(R.id.toolbar);
        tvEmail = getView().findViewById(R.id.tv_email_profile_content);
        tvUsername = getView().findViewById(R.id.tv_username_profile_content);
        tvPhone = getView().findViewById(R.id.tv_phone_profile_content);
        etEditUsername = getView().findViewById(R.id.et_edit_username);
        btnEdit = getView().findViewById(R.id.btn_edit);
        btnSave = getView().findViewById(R.id.btn_save);
        btnDelete = getView().findViewById(R.id.btn_delete);
        btnLogout = getView().findViewById(R.id.btn_logout);

        toolbar.setSubtitle(loggedInUsername);
        tvEmail.setText(loggedInEmail);
        tvUsername.setText(loggedInUsername);
        tvPhone.setText(loggedInPhone);

        btnEdit.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        UserRepository userRepo = SharedData.USER_REPOSITORY;

        if (view == btnEdit) {
            tvUsername.setVisibility(View.INVISIBLE);
            etEditUsername.setVisibility(View.VISIBLE);
            etEditUsername.setText(loggedInUsername);
            btnEdit.setVisibility(View.GONE);
            btnSave.setVisibility(View.VISIBLE);
        } else if (view == btnSave) {
            String newUsername = etEditUsername.getText().toString().trim();

            if (newUsername.length() > 2 && newUsername.length() < 21) {
                User user = userRepo.findByUsername(newUsername);

                if (user != null) {
                    GenericHelper.toastMaker(getContext(), "Username already exists!");
                } else {
                    user = userRepo.findByUsername(loggedInUsername);
                    userRepo.updateUsernameById(user.getId(), newUsername);

                    tvUsername.setVisibility(View.VISIBLE);
                    etEditUsername.setVisibility(View.GONE);
                    toolbar.setSubtitle(newUsername);
                    tvUsername.setText(newUsername);
                    btnSave.setVisibility(View.GONE);
                    btnEdit.setVisibility(View.VISIBLE);

                    SharedPreferences.Editor spEdit = sp.edit();
                    spEdit.putString("username", newUsername).apply();
                }
            } else {
                GenericHelper.toastMaker(getContext(), "Length between 3-20 characters!");
            }
        } else if (view == btnDelete) {
            new MaterialAlertDialogBuilder(getContext())
                    .setIcon(R.drawable.ic_baseline_error_24)
                    .setTitle("Confirmation")
                    .setMessage("Are you sure want to delete account?")
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        // DO NOTHING
                    })
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        User user = SharedData.USER_REPOSITORY.findByUsername(loggedInUsername);
                        SharedData.USER_REPOSITORY.delete(user.getId());
                        GenericHelper.toastMaker(getContext(), "Successfully deleted");
                        getActivity().finish();
                    })
                    .show();
        } else if (view == btnLogout) {
            sp = this.getActivity().getSharedPreferences("LOGGED_IN_USER", MODE_PRIVATE);
            sp.edit().clear().apply();

            Log.d("TestingData", loggedInUsername);

            this.getActivity().finish();
        }
    }
}