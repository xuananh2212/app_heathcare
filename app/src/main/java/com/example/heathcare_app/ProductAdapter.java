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
import com.example.heathcare_app.model.Medicine;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Medicine> productList;

    public ProductAdapter(Context context, List<Medicine> productList) {
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
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        Medicine product = productList.get(position);
        float priceNew = (float) product.getNewPrice();
        float priceOld = (float) product.getOldPrice();
        holder.titleProduct.setText(product.getName());
        holder.oldPrice.setText(vndFormat.format(priceOld));
        holder.txtPrice.setText(vndFormat.format(priceNew));
//        holder.oldPrice.setText(product.getOldPrice() + "đ");
//        holder.txtPrice.setText(product.getNewPrice() + "đ");
        Glide.with(context).load(product.getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BuyMedicineBookActivity.class);
            intent.putExtra("id", product.getId());
            intent.putExtra("imageUrl", product.getImage());
            intent.putExtra("title", product.getName());
//            intent.putExtra("oldPrice", vndFormat.format(priceOld));
//            intent.putExtra("newPrice", vndFormat.format(priceNew));
            intent.putExtra("oldPrice", priceOld);
            intent.putExtra("newPrice", priceNew );
            intent.putExtra("desc", product.getDescription());
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