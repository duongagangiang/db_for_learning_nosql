package com.example.pcc.appringtone;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcc.appringtone.Model.Image;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Detailactivity extends AppCompatActivity {

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailactivity);
        img=(ImageView)findViewById(R.id.imageView);
        String url=getIntent().getStringExtra("data");
        String tieude=getIntent().getStringExtra("title");
        Log.d("data",""+url);
        Picasso.with(this).load(url).fit().centerCrop().into(img);
        ActionBar actionbar=getSupportActionBar();
        actionbar.setTitle(tieude);
        actionbar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId()==R.id.imgSetwall)
            {
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
            if(item.getItemId()==android.R.id.home){
                finish();
            }
        return super.onOptionsItemSelected(item);
    }
}
