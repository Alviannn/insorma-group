package dev.juviga.insorma.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import dev.juviga.insorma.R;
import dev.juviga.insorma.ui.about.MapsFragment;
import dev.juviga.insorma.ui.home.MainPage;
import dev.juviga.insorma.ui.profile.ProfilePage;
import dev.juviga.insorma.ui.transaction.TransactionPage;

public class MainActivity extends AppCompatActivity {

    private String loggedInUsername, loggedInEmail, loggedInPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("LOGGED_IN_USER", MODE_PRIVATE);
        loggedInUsername = sp.getString("username", "<<EMPTY>>");
        loggedInEmail = sp.getString("email", "<<EMPTY>>");
        loggedInPhone = sp.getString("phone", "<<EMPTY>>");

        Log.d("TestingData", loggedInUsername);
    }

    public void toMainPage(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new MainPage());
        Log.d("Test fragment", "Masuk");
        fragmentTransaction.commit();
    }

    public void toTransactionPage(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new TransactionPage());
        fragmentTransaction.commit();
    }

    public void toProfilePage(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new ProfilePage());
        fragmentTransaction.commit();
    }

    public void toAboutPage(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new MapsFragment());
        fragmentTransaction.commit();
    }
}