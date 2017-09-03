package com.example.pcc.appringtone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Category extends AppCompatActivity {
    int category=0;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        txt=(TextView)findViewById(R.id.appwallpapers);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category,menu);
        return super.onCreateOptionsMenu(menu);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //region category
            case R.id._abstract:
                if(item.isChecked())
                    item.setChecked(false);
                else{
                item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/abstract/page/");

                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.android:
                if(item.isChecked())
                    item.setChecked(false);
                else{
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/android/page/");

                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.animals:
                if(item.isChecked())
                    item.setChecked(false);
                else{
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/animals/page/");

                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.architecture:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/architecture/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.cars_bikes:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/cars-bikes/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.cartoons:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/cartoons/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.colorful:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/colorful/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.food_drinks:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/food-drinks/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.funny:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/funny/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.games:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/games/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.girls:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/girls/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.guys:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/guys/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.holiday:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/holiday/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.illustrations:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/illustrations/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.logo:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/logo/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.misc:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/misc/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.movies:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/movies/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.music:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/music/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.nature:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/nature/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.patterns:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/patterns/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.places:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/places/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.science_fiction:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/science-fiction/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.sexy:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/sexy/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.space:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/space/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.sports:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/sports/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.technology:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/technology/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.textures:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/textures/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.typography:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/typography/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            case R.id.alls:
                if(item.isChecked())
                    item.setChecked(false);
                else {
                    item.setChecked(true);
                    txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(Category.this,MainActivity.class);
                            i.putExtra("url","http://androidwalls.net/page/");
                            startActivity(i);
                        }
                    });
                }
                break;
            //endregion

        }
        return super.onOptionsItemSelected(item);
    }
}
