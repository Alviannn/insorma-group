package dev.juviga.insorma.ui;

import static dev.juviga.insorma.ui.MainActivity.currentMenu;
import static dev.juviga.insorma.ui.adapter.ProductDataAdapter.productPosition;
import static dev.juviga.insorma.ui.home.MainFragment.products;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

import dev.juviga.insorma.R;
import dev.juviga.insorma.data.model.Transaction;
import dev.juviga.insorma.data.repository.ProductRepository;
import dev.juviga.insorma.data.repository.TransactionRepository;
import dev.juviga.insorma.data.repository.UserRepository;
import dev.juviga.insorma.services.sms.SmsService;


public class DetailActivity extends AppCompatActivity {

    public static int quantity = 0;
    //declare
    TextView titleText, priceText, ratingText, descriptionText;
    Button buttonBuy;
    EditText quantityBox;
    ImageView productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //initialize
        productImage = findViewById(R.id.productImage);
        titleText = findViewById(R.id.titleText);
        priceText = findViewById(R.id.priceText);
        ratingText = findViewById(R.id.ratingText);
        descriptionText = findViewById(R.id.descriptionText);
        quantityBox = findViewById(R.id.quantityBox);
        buttonBuy = findViewById(R.id.buttonBuy);

        //set
        titleText.setText(products.get(productPosition).getName());
        priceText.setText(String.valueOf(products.get(productPosition).getPrice()));
        ratingText.setText(String.valueOf(products.get(productPosition).getRating()));
        descriptionText.setText(products.get(productPosition).getDescription());
        Glide.with(this)
                .load(products.get(productPosition).getImageUrl())
                .into(productImage);

        //event
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity = Integer.parseInt(quantityBox.getText().toString().trim());

                //getUserID
                SharedPreferences sp = getSharedPreferences("LOGGED_IN_USER", Context.MODE_PRIVATE);
                String loggedInUsername = sp.getString("username", "");
                String loggedInPhone = sp.getString("phone", "");

                UserRepository userRepository = new UserRepository();
                int idUser = userRepository.findByUsername(loggedInUsername).getId();

                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                ProductRepository productRepository = new ProductRepository();
                String productId = products.get(productPosition).getName();

                Transaction newTransaction = new Transaction(0, idUser, productId, date, quantity);

                TransactionRepository transactionRepository = new TransactionRepository();
                transactionRepository.insert(newTransaction);

                currentMenu = 1;

                int permission = DetailActivity.this.checkSelfPermission(Manifest.permission.SEND_SMS);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    DetailActivity.this.requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                }

                SmsService service = new SmsService(DetailActivity.this.getApplicationContext());
                service.sendMessage(loggedInPhone,
                        String.format(
                                "You have bought the product!\nProduct Name: %s\nQuantity: %d\nTotal Price: %d x %d = %d",
                                products.get(productPosition).getName(),
                                quantity,
                                products.get(productPosition).getPrice(),
                                quantity,
                                (products.get(productPosition).getPrice() * quantity)
                        )
                );

                finish();
            }
        });

    }
}