package dev.juviga.insorma.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.juviga.insorma.R;
import dev.juviga.insorma.data.model.Transaction;

public class TransactionDataAdapter extends RecyclerView.Adapter<TransactionDataAdapter.ViewHolder> {

    private final List<Transaction> transactions;

    public TransactionDataAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
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
        holder.applyData(transaction);
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

        public void applyData(Transaction data) {
            // TODO: apply all data, use glide here
        }

    }

}
