package com.example.pcc.appringtone.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pcc.appringtone.MainActivity;
import com.example.pcc.appringtone.Model.Image;
import com.example.pcc.appringtone.Model.Mp3;
import com.example.pcc.appringtone.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcc on 03/09/2017.
 */

public class GridViewAdapter extends ArrayAdapter<Image> {
    Context context;
    List<Image> list;
    public GridViewAdapter(Context context,int id,List<Image> list){
        super(context,id,list);
        this.context=context;
        this.list=list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=view;
        if(view==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            v=inflater.inflate(R.layout.gridview_item,null);
        }
        TextView td;
        ImageView img;
        td=(TextView)v.findViewById(R.id.tieude);
        img=(ImageView)v.findViewById(R.id.img);

        td.setText(list.get(i).getTieude());
        Picasso.with(getContext()).load(list.get(i).getLink()).fit().centerCrop().into(img);
        return v;
    }
}