package com.example.pcc.doctintucrss;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcc on 01/10/2017.
 */

public class NewsAdapter extends ArrayAdapter<NewsModel> {
    LayoutInflater inflater;
    public NewsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<NewsModel> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.inflater=inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_listview,null);
        }
        ImageView img=(ImageView)convertView.findViewById(R.id.img_thumnail);
        TextView txt=(TextView)convertView.findViewById(R.id.txt_title);
        NewsModel md=getItem(position);
        Picasso.with(inflater.getContext()).load(md.getImage()).into(img);
        txt.setText(md.getTitle());
        return convertView;
    }
}
