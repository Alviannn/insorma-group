package dev.juviga.insorma.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import dev.juviga.insorma.R;
import dev.juviga.insorma.data.model.Product;
import dev.juviga.insorma.data.model.Transaction;

public class TransactionDataAdapter extends RecyclerView.Adapter<TransactionDataAdapter.ViewHolder> {

    private final List<Transaction> transactions;
    Context context;
    public TransactionDataAdapter(Context context, List<Transaction> transactions) {
        this.transactions = transactions;
        if(this.transactions.size() == 0) return;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.template_transaction, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.applyData(context, transaction);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView name, rating, price;

        public ViewHolder(@NonNull View view) {
            super(view);

            this.image = view.findViewById(R.id.product_image);
            this.name = view.findViewById(R.id.product_name);
            this.rating = view.findViewById(R.id.product_rating);
            this.price = view.findViewById(R.id.product_price);
        }

        public void applyData(Context context, Transaction data) {
            Product product = data.getProduct();
            if (product == null) {
                throw new NullPointerException("`transaction` doesn't populate `product`");
            }

            name.setText(product.getName());
            rating.setText(product.getRating() + " / 5.0");
            price.setText("$" + product.getPrice() * data.getQuantity());
            Glide.with(context)
                    .load(product.getImageUrl())
                    .into(image);
        }

    }

}
