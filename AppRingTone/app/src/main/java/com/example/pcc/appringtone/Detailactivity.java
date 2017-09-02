package com.example.pcc.appringtone;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcc.appringtone.Model.Image;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Detailactivity extends AppCompatActivity {

    ImageView img,imgset;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailactivity);
        img=(ImageView)findViewById(R.id.imageView);
        imgset=(ImageView)findViewById(R.id.imgSetwall);
        txt=(TextView) findViewById(R.id.txttitle);
        String url=getIntent().getStringExtra("data");
        String tieude=getIntent().getStringExtra("title");
        Log.d("data",""+url);
        Picasso.with(this).load(url).fit().centerCrop().into(img);
        txt.setText(tieude);
        imgset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
                img.buildDrawingCache();
                Bitmap bm=img.getDrawingCache();
                try {
                    wallpaperManager.setBitmap(bm);
                    Toast.makeText(getApplicationContext(),"Cài đặt hình nền điện thoại thành công",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
