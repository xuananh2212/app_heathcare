package com.example.heathcare_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_pop_list, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.titleProduct.setText(product.getTitle());
        holder.oldPrice.setText(product.getOldPrice() + "đ");
        holder.txtPrice.setText(product.getPrice() + "đ");
        Glide.with(context).load(product.getImageUrl()).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BuyMedicineBookActivity.class);
            intent.putExtra("imageUrl", product.getImageUrl());
            intent.putExtra("title", product.getTitle());
            intent.putExtra("oldPrice", product.getOldPrice()+"đ");
            intent.putExtra("newPrice", product.getPrice()+"đ");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleProduct, oldPrice, txtPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleProduct = itemView.findViewById(R.id.titleProduct);
            oldPrice = itemView.findViewById(R.id.oldPrice);
            oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}