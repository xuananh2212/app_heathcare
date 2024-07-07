package com.example.heathcare_app.model;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.heathcare_app.R;
import com.example.heathcare_app.api.ApiService;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartsAdapterPayed extends RecyclerView.Adapter<CartsAdapterPayed.ItemCartsViewHolder> {
    private List<ItemCarts> productList;
    private OnQuantityChangeListener onQuantityChangeListener;
    private boolean updateSucces;
    private List<Integer> arrayNumber;


    public interface OnQuantityChangeListener {
        void onQuantityChanged();
    }

    public CartsAdapterPayed(List<ItemCarts> productList, OnQuantityChangeListener onQuantityChangeListener, List<Integer> arrayNumber) {
        this.productList = productList;
        this.arrayNumber = arrayNumber;
        this.onQuantityChangeListener = onQuantityChangeListener;
    }

    public static class ItemCartsViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvProductPrice, tvQuantity;
        Button btnDecrease, btnIncrease, btnRemoveItem;
        ImageView imageView;

        public ItemCartsViewHolder(View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            imageView = itemView.findViewById(R.id.itemProductImage);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnRemoveItem = itemView.findViewById(R.id.btnRemove);
        }
    }

    @NonNull
    @Override
    public ItemCartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ItemCartsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCartsViewHolder holder, int position) {
        ItemCarts product = productList.get(position);
        holder.tvProductName.setText(product.getName());
//        holder.tvProductPrice.setText("Giá: " + product.getPrice() + " VND");
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        float price = (float) product.getPrice();
        String formattedPrice = vndFormat.format(price);
        // Định dạng giá trị price sang VND
//        holder.tvProductPrice.setText(String.format("Giá: %.2f VND", product.getPrice()));
        holder.tvProductPrice.setText(formattedPrice);
        Log.d("responseapi",product.getUrlImage());
        holder.tvQuantity.setText(String.valueOf(product.getQuantity()));
        String imageUrl = product.getUrlImage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Log.d("responseapi","url_img ok");
            if (holder.itemView.getContext() != null) {
                // Ensure the ImageView is not null
                if (holder.imageView != null) {
                    // Load the image using Glide
                    Glide.with(holder.itemView.getContext())
                            .load(imageUrl)
                            .apply(new RequestOptions()
                                    .placeholder(R.drawable.medicine1) // Set a placeholder image
                                    .error(R.drawable.error))           // Set an error image if load fails
                            .into(holder.imageView);
                } else {
                    Log.d("responseapi", "ImageView is null at position " + position);
                }
            } else {
                Log.d("responseapi", "Context is null at position " + position);
            }
        } else {
            Log.d("responseapi","url_img  k  ok");
            // Set a placeholder image if URL is null or empty
            holder.imageView.setImageResource(R.drawable.medicine2);
        }
    }


    @Override
    public void unregisterAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }
    // code them
    public interface ApiCallback {
        void onSuccess();
        void onFailure();
    }
     // code them
}
