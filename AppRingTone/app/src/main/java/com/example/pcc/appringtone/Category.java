package com.example.pcc.appringtone;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.pcc.appringtone.Adapter.GridViewCategoryAdapter;
import com.example.pcc.appringtone.Model.ModelCategory;

import java.util.ArrayList;
import java.util.List;

public class Category extends AppCompatActivity{
    GridView gridView;
    GridViewCategoryAdapter adapter;
    List<ModelCategory> arrCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_category);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("List Wallpapers");

        gridView=(GridView)findViewById(R.id.gridViewCategory);

        setData();
        adapter=new GridViewCategoryAdapter(Category.this,R.layout.gridviewcategory_item,arrCategory);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Category.this, MainActivity.class);
                intent.putExtra("url",arrCategory.get(i).getLink());
                intent.putExtra("title", arrCategory.get(i).getName());
                startActivity(intent);
            }
        });

    }
   private void setData(){
       arrCategory=new ArrayList<>();
       arrCategory.add(new ModelCategory("Alls","http://androidwalls.net/page/"));
       arrCategory.add(new ModelCategory("Abstract","http://androidwalls.net/abstract/page/"));
       arrCategory.add(new ModelCategory("Android","http://androidwalls.net/android/page/"));
       arrCategory.add(new ModelCategory("Animals","http://androidwalls.net/animals/page/"));
       arrCategory.add(new ModelCategory("Architecture","http://androidwalls.net/architecture/page/"));
       arrCategory.add(new ModelCategory("Cars & Bikes","http://androidwalls.net/cars-bikes/page/"));
       arrCategory.add(new ModelCategory("Cartoons","http://androidwalls.net/cartoons/page/"));
       arrCategory.add(new ModelCategory("Colorful","http://androidwalls.net/colorful/page/"));
       arrCategory.add(new ModelCategory("Food & Drinks","http://androidwalls.net/food-drinks/page/"));
       arrCategory.add(new ModelCategory("Funny","http://androidwalls.net/funny/page/"));
       arrCategory.add(new ModelCategory("Games","http://androidwalls.net/games/page/"));
       arrCategory.add(new ModelCategory("Girls","http://androidwalls.net/celebrities-girls/page/"));
       arrCategory.add(new ModelCategory("Guys","http://androidwalls.net/celebrities-guys/page/"));
       arrCategory.add(new ModelCategory("Holiday","http://androidwalls.net/holiday/page/"));
       arrCategory.add(new ModelCategory("Illustrations","http://androidwalls.net/illustrations/page/"));
       arrCategory.add(new ModelCategory("Logo","http://androidwalls.net/logo/page/"));
       arrCategory.add(new ModelCategory("Misc","http://androidwalls.net/misc/page/"));
       arrCategory.add(new ModelCategory("Movies","http://androidwalls.net/movies/page/"));
       arrCategory.add(new ModelCategory("Music","http://androidwalls.net/music/page/"));
       arrCategory.add(new ModelCategory("Nature","http://androidwalls.net/nature/page/"));
       arrCategory.add(new ModelCategory("Patterns","http://androidwalls.net/patterns/page/"));
       arrCategory.add(new ModelCategory("Places","http://androidwalls.net/places/page/"));
       arrCategory.add(new ModelCategory("Science fiction","http://androidwalls.net/science-fiction/page/"));
       arrCategory.add(new ModelCategory("Sexy","http://androidwalls.net/sexy-hot/page/"));
       arrCategory.add(new ModelCategory("Space","http://androidwalls.net/space/page/"));
       arrCategory.add(new ModelCategory("Sports","http://androidwalls.net/sports/page/"));
       arrCategory.add(new ModelCategory("Technology","http://androidwalls.net/technology/page/"));
       arrCategory.add(new ModelCategory("Textures","http://androidwalls.net/textures/page/"));
       arrCategory.add(new ModelCategory("Typography","http://androidwalls.net/typography/page/"));

   }
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
////                Intent intent=new Intent(Category.this,MainActivity.class);
////                intent.putExtra("url",arrCategory.get(i).getLink());
////                startActivity(intent);
//
//
//    }
}
