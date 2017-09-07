package com.example.pcc.appringtone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.pcc.appringtone.Model.ModelCategory;
import com.example.pcc.appringtone.R;

import java.util.List;

/**
 * Created by pcc on 04/09/2017.
 */

public class GridViewCategoryAdapter extends ArrayAdapter<ModelCategory> {
    Context context;
    List<ModelCategory> list;
    public GridViewCategoryAdapter(Context context,int id,List<ModelCategory> list){
        super(context,id,list);
        this.context=context;
        this.list=list;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=view;
        if(view==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            v=inflater.inflate(R.layout.gridviewcategory_item,null);
        }
        TextView btn;
        btn=(TextView) v.findViewById(R.id.btn);
        btn.setText(list.get(i).getName());
        return v;
    }
}