package dev.juviga.insorma.ui;

import static dev.juviga.insorma.ui.adapter.ProductDataAdapter.productPosition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dev.juviga.insorma.R;
import dev.juviga.insorma.ui.home.MainFragment;


public class DetailActivity extends AppCompatActivity {

    //declare
    TextView titleText, priceText, ratingText, descriptionText;
    Button buttonBuy;
    EditText quantityBox;
    public static int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //initialize
        titleText = findViewById(R.id.titleText);
        priceText = findViewById(R.id.priceText);
        ratingText = findViewById(R.id.ratingText);
        descriptionText = findViewById(R.id.descriptionText);
        quantityBox = findViewById(R.id.quantityBox);
        buttonBuy = findViewById(R.id.buttonBuy);

        //set
        titleText.setText(MainFragment.products.get(productPosition).getName());
        priceText.setText(String.valueOf(MainFragment.products.get(productPosition).getPrice()));
        ratingText.setText(String.valueOf(MainFragment.products.get(productPosition).getRating()));
        descriptionText.setText(MainFragment.products.get(productPosition).getDescription());

        //event
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity = Integer.valueOf(quantityBox.getText().toString().trim());
            }
        });

    }
}