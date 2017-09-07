package com.example.pcc.appringtone;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcc.appringtone.Model.Image;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Detailactivity extends AppCompatActivity {

    ImageView img;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_detailactivity);
        img=(ImageView)findViewById(R.id.imageView);
        String url=getIntent().getStringExtra("data");
        String tieude=getIntent().getStringExtra("title");
        Log.d("data",""+url);
        Picasso.with(this).load(url).fit().centerCrop().into(img);
        ActionBar actionbar=getSupportActionBar();
        actionbar.setTitle(tieude);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#bf000000")));


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
                DisplayMetrics displayMetrics=new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height=displayMetrics.heightPixels;
                int width=displayMetrics.widthPixels;
                img.buildDrawingCache();
                Bitmap temp_bm=img.getDrawingCache();
                Bitmap bm=Bitmap.createScaledBitmap(temp_bm,width,height,true);

                WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
                wallpaperManager.setWallpaperOffsetSteps(1,1);
                wallpaperManager.suggestDesiredDimensions(width,height);

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
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }
}
