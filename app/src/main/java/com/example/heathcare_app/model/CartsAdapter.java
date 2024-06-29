package com.example.heathcare_app.model;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.heathcare_app.OrderDetailsActivity;
import com.example.heathcare_app.R;
import com.example.heathcare_app.api.ApiService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartsAdapter extends RecyclerView.Adapter<CartsAdapter.ItemCartsViewHolder> {
    private List<ItemCarts> productList;
    private OnQuantityChangeListener onQuantityChangeListener;
    private boolean updateSucces;
    private List<Integer> arrayNumber;


    public interface OnQuantityChangeListener {
        void onQuantityChanged();
    }

    public CartsAdapter(List<ItemCarts> productList, OnQuantityChangeListener onQuantityChangeListener, List<Integer> arrayNumber) {
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
        holder.tvProductPrice.setText(String.format("Giá: %.2f VND", product.getPrice()));
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
        holder.btnDecrease.setOnClickListener(v -> {
            Log.d("responseapi", product.toString());
            if (product.getQuantity() > 1) {
                int currentQuantity = product.getQuantity() - 1;
                BodyUpdateCart payload = new BodyUpdateCart(currentQuantity);
                Log.d("responseapi", payload.toString());
                BodyUpdate bodyUpdate = new BodyUpdate(product.getId(), payload);
                Log.d("responseapi", bodyUpdate.toString());
//                callApiUpdateItemOrderDetails(bodyUpdate,);
//                Log.d("responseapi", "Value statusupdate: " + updateSucces);
//                if (updateSucces) {
//                    product.setQuantity(product.getQuantity() - 1);
//                    holder.tvQuantity.setText(String.valueOf(product.getQuantity()));
//                    onQuantityChangeListener.onQuantityChanged();
//                }
//                updateSucces = false;
                callApiUpdateItemOrderDetails(bodyUpdate ,new ApiCallback(){
                    public void onSuccess() {
                        Log.d("responseapi", "Value statusupdate: " + getUpdateSucces());
                        product.setQuantity(product.getQuantity() - 1);
                        holder.tvQuantity.setText(String.valueOf(product.getQuantity()));
                        onQuantityChangeListener.onQuantityChanged();
                    }

                    @Override
                    public void onFailure() {
                        // Handle failure case
                    }
                });
            }
        });

        holder.btnIncrease.setOnClickListener(v -> {
            Log.d("responseapi", "Id carts: " + product.getId());
            int currentQuantity = product.getQuantity() + 1;
            BodyUpdateCart payload = new BodyUpdateCart(currentQuantity);
            Log.d("responseapi", payload.toString());
            BodyUpdate bodyUpdate = new BodyUpdate(product.getId(), payload);
            Log.d("responseapi", bodyUpdate.toString());
            callApiUpdateItemOrderDetails(bodyUpdate ,new ApiCallback(){
                public void onSuccess() {
                    Log.d("responseapi", "Value statusupdate: " + getUpdateSucces());
                    product.setQuantity(product.getQuantity() + 1);
                    holder.tvQuantity.setText(String.valueOf(product.getQuantity()));
                    onQuantityChangeListener.onQuantityChanged();
                }

                @Override
                public void onFailure() {
                    // Handle failure case
                }
            });
            Log.d("responseapi", "Value statusupdate: " + getUpdateSucces());
//            if (getUpdateSucces()) {
//                product.setQuantity(product.getQuantity() + 1);
//                holder.tvQuantity.setText(String.valueOf(product.getQuantity()));
//                onQuantityChangeListener.onQuantityChanged();
//            }
            setUpdateSucces(false);
            Log.d("responseapi", "Value updateSuccess after gan bang false: " + updateSucces);
        });
        holder.btnRemoveItem.setOnClickListener(v -> {
            DialogRemoveItem.showDialog(holder.itemView.getContext(), "Are you sure you want to remove this item?", new DialogRemoveItem.OnRemoveListener() {
                @Override
                public void onRemove() {
                    String id = Integer.toString(product.getId());
                    Log.d("responseapi", id);
                    int idUser = Integer.parseInt(id);
                    BodyDeleteCart bodyDeleteCart = new BodyDeleteCart((idUser));
                    Log.d("responseapi", bodyDeleteCart.toString());
                    callApiRemoveItem(idUser, bodyDeleteCart, position);
                    Log.d("responseapi", "call xong api");
                }
            });
        });
    }

    public void callApiRemoveItem(int idUser,BodyDeleteCart bodyDeleteCart,int position) {
        Call<ApiResponse<Integer>> call  = ApiService.apiService.handleRemoveItemOrdeDetails(idUser);
        call.enqueue(new Callback<ApiResponse<Integer>>() {
            @Override
            public void onResponse(Call<ApiResponse<Integer>> call, Response<ApiResponse<Integer>> response) {
                Log.d("responseapi","Call Api success");
                if(response.isSuccessful() && response.body() != null) {
                    ApiResponse<Integer> removeItemResponse = response.body();
                    Log.d("responseapi",removeItemResponse.getMessage());
                    arrayNumber.remove(Integer.valueOf(idUser));
                    productList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, productList.size());
                    onQuantityChangeListener.onQuantityChanged();
                } else {
                    try {
                        if (response.errorBody() != null) {
                            String errorBody = response.errorBody().string();
                            ApiResponse<Integer> errorResponse = new Gson().fromJson(errorBody, ApiResponse.class);
                            Log.d("responseapi", "Parsed error response: " + errorResponse.toString());
                            //Toast.makeText(getApplicationContext(),errorResponse.getMessage(),Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("responseapi", "ErrorBody is null");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Integer>> call, Throwable t) {
 Log.d("responseapi","Api loi");
            }
        });
    }

    @Override
    public void unregisterAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
    }

    public void callApiUpdateItemOrderDetails(BodyUpdate<BodyUpdateCart> bodyUpdate,ApiCallback callback) {
        Call<ApiResponse<Object>> call = ApiService.apiService.handleUpdateItemOrderDetails(bodyUpdate);
        call.enqueue(new Callback<ApiResponse<Object>>() {

            @Override
            public void onResponse(Call<ApiResponse<Object>> call, Response<ApiResponse<Object>> response) {
                Log.d("responseapi", "Vao goi api success");
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("responseapi", response.body().toString());
//                    updateSucces = true;
                    setUpdateSucces(true);
                    // code them
                    callback.onSuccess();
                    // end
                    Log.d("responseapi", "Value statusupdate after update = true call api: " + updateSucces);
                } else {
                    Log.d("responseapi", "Cap nhat loi");
                    //updateSucces = false;
                    // code them
                    callback.onFailure();
                    // end
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Object>> call, Throwable t) {
                Log.d("responseapi", "Vao goi api fail");
                t.printStackTrace();
                Log.d("responseapi", "Call Api lỗi");
                //Toast.makeText(OrderDetailsActivity.this, "Call Api Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean callApiUpdateItemOrderDetails1(BodyUpdate<BodyUpdateCart> bodyUpdate) {
        Call<ApiResponse<Object>> call = ApiService.apiService.handleUpdateItemOrderDetails(bodyUpdate);
        call.enqueue(new Callback<ApiResponse<Object>>() {
            @Override
            public void onResponse(Call<ApiResponse<Object>> call, Response<ApiResponse<Object>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("responseapi", response.body().toString());
//                    updateSucces = true;
                    setUpdateSucces(true);
                } else {

                    // updateSucces = false;
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Object>> call, Throwable t) {
                t.printStackTrace();
                Log.d("responseapi", "Call Api lỗi");
                //Toast.makeText(OrderDetailsActivity.this, "Call Api Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
        if (updateSucces)
            return true;
        return false;
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

    private synchronized boolean getUpdateSucces() {
        return updateSucces;
    }

    private synchronized void setUpdateSucces(boolean value) {
        updateSucces = value;
    }
     // code them
}
