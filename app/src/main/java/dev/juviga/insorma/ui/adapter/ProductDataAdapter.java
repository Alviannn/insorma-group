package dev.juviga.insorma.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.juviga.insorma.R;

public class ProductDataAdapter extends RecyclerView.Adapter<ProductDataAdapter.HolderData> {

    List<String>productTitle;
    LayoutInflater inflater;

    public ProductDataAdapter(Context context, List<String>productTitle) {
        this.productTitle = productTitle;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.template_product, parent, false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        holder.productTitle.setText(productTitle.get(position));
    }

    @Override
    public int getItemCount() {
        return productTitle.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {

        TextView productTitle;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            productTitle = itemView.findViewById(R.id.productTitle);
        }
    }
}
