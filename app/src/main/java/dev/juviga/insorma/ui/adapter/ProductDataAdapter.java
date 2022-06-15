package dev.juviga.insorma.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.juviga.insorma.R;
import dev.juviga.insorma.data.model.Product;
import dev.juviga.insorma.ui.DetailActivity;
import dev.juviga.insorma.ui.MainActivity;

public class ProductDataAdapter extends RecyclerView.Adapter<ProductDataAdapter.HolderData> {

    List<Product>products;
    LayoutInflater inflater;
    Context context;
    public static int productPosition;

    public ProductDataAdapter(Context context, List<Product>products) {
        this.products = products;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.template_product, parent, false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        holder.productTitle.setText(products.get(position).getName());
        holder.productPrice.setText(String.valueOf(products.get(position).getPrice()));
        holder.productRating.setText(String.valueOf(products.get(position).getRating()));
        holder.productContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toDetailPage = new Intent(context, DetailActivity.class);

                productPosition = holder.getPosition();

                Log.d("Test Context", context instanceof MainActivity ? "Yes" : "No");
                Log.d("Test Context", inflater.getContext() instanceof MainActivity ? "Yes" : "No");

                inflater.getContext().startActivity(toDetailPage);

            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {

        TextView productTitle, productPrice, productRating;
        LinearLayout productContainer;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            productTitle = itemView.findViewById(R.id.productTitle);
            productPrice = itemView.findViewById(R.id.productPrice);
            productRating = itemView.findViewById(R.id.productRating);
            productContainer = itemView.findViewById(R.id.productContainer);
        }
    }
}
