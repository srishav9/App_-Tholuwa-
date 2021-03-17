package com.example.hp.dc_project;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.hp.dc_project.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends ArrayAdapter<workers> {
    private Activity context;
    private int resource;
    private List<workers> listImage;

    public MyAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<workers> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        listImage = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View v = inflater.inflate(resource, null);
        TextView tv = (TextView) v.findViewById(R.id.employeename);
        ImageView iv = (ImageView) v.findViewById(R.id.usericon);

        tv.setText(listImage.get(position).getTitle());
        Picasso.with(context).load(listImage.get(position).getImage()).into(iv);

        return v;

    }

    public void addElement(workers element) {

        listImage.add(element);
    }
}