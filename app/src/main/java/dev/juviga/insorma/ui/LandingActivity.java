package dev.juviga.insorma.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import dev.juviga.insorma.R;
import dev.juviga.insorma.data.db.DatabaseHelper;
import dev.juviga.insorma.data.shared.SharedData;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        SharedData.DATABASE_HELPER = new DatabaseHelper(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedData.DATABASE_HELPER.close();
    }

}