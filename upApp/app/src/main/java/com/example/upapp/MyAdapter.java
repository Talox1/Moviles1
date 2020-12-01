package com.example.upapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<User> {

    Context context;
    List<User> arrayListUser;


    public MyAdapter(@NonNull Context context, List<User> arrayListUser) {
        super(context, R.layout.custom_list_layout, arrayListUser);
        this.context = context;
        this.arrayListUser = arrayListUser;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_layout, null, true);
        TextView tvID = view.findViewById(R.id.txt_list_id);
        TextView tvName = view.findViewById(R.id.txt_list_name);

        tvID.setText(arrayListUser.get(position).getId());
        tvName.setText(arrayListUser.get(position).getNombre());
        return view;
    }
}
