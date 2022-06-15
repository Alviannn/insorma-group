package dev.juviga.insorma.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import dev.juviga.insorma.R;
import dev.juviga.insorma.data.db.DatabaseHelper;
import dev.juviga.insorma.data.repository.ProductRepository;
import dev.juviga.insorma.data.repository.TransactionRepository;
import dev.juviga.insorma.data.repository.UserRepository;
import dev.juviga.insorma.data.shared.SharedData;

public class LandingActivity extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        SharedData.DATABASE_HELPER = new DatabaseHelper(getApplicationContext());

        SharedData.USER_REPOSITORY = new UserRepository();
        SharedData.PRODUCT_REPOSITORY = new ProductRepository();
        SharedData.TRANSACTION_REPOSITORY = new TransactionRepository();

        //coba intent dulu buat cek main activity
        Intent toMainActivity = new Intent(this, MainActivity.class);
        startActivity(toMainActivity);

        // TODO: use code on home view
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://bit.ly/")
//                .build();
//
//        FurnitureService service = retrofit.create(FurnitureService.class);
//        service.getFurnitures().enqueue(new FurnituresCallback());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedData.DATABASE_HELPER.close();
    }

}