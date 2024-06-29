package com.example.heathcare_app.model;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.heathcare_app.R;

public class DialogRemoveItem  {


    public interface OnRemoveListener {
        void onRemove();
    }

    public static void showDialog(Context context, String message, final OnRemoveListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.activity_dialog_remove_item, null);
        builder.setView(dialogView);
        TextView dialogMessage = dialogView.findViewById(R.id.dialog_message);
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        Button btnRemove = dialogView.findViewById(R.id.btn_confirm);

        dialogMessage.setText(message);

        final AlertDialog dialog = builder.create();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRemove();
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}