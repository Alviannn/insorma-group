package dev.juviga.insorma.ui.register;

import com.google.android.material.textfield.TextInputLayout;

import dev.juviga.insorma.data.model.User;
import dev.juviga.insorma.data.shared.SharedData;

public class RegisterValidation {

    public static boolean isEmailOk(String email, TextInputLayout tilEmail) {
        if (!email.endsWith(".com")) {
            tilEmail.setError("Must end with '.com'!");
            return false;
        }
        return true;
    }

    public static boolean isUsernameOk(String username, TextInputLayout tilUsername) {
        if (username.length() < 3 || username.length() > 20) {
            tilUsername.setError("Length between 3-20 characters!");
            return false;
        }
        return true;
    }

    public static boolean isPasswordOk(String password, TextInputLayout tilPassword) {
        if (!password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$")) {
            tilPassword.setError("Must contain letters and numbers!");
            return false;
        }
        return true;
    }

    public static boolean isUnique(String username, String email, TextInputLayout tilUsername, TextInputLayout tilEmail) {
        User user = SharedData.USER_REPOSITORY.findByUsernameAndEmail(username, email);

        if (user != null) {
            tilUsername.setError("Already Exists!");
            tilEmail.setError("Already Exists!");
            return false;
        }
        return true;
    }
}
