package com.example.pcc.appringtone;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.pcc.appringtone.Adapter.GridViewAdapter;
import com.example.pcc.appringtone.Model.Image;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    int j=1;
    String url;
    List<Image> arr=new ArrayList<>();
    GridView gridView;
    GridViewAdapter adapter;
    public boolean isLoading=false;
    List<Image> arrTotal=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url=getIntent().getStringExtra("url");
        gridView=(GridView)findViewById(R.id.gridView);
        try {
            arr=new getImage().execute(j).get();
            arrTotal.addAll(arr);
            Log.d("data",""+j);
            Log.d("profile",""+arr);
            adapter=new GridViewAdapter(MainActivity.this,R.layout.gridview_item,arrTotal);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(this);
            gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {

                }

                @Override
                public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                    if( i+i1==i2 && isLoading==false){
                        j=j+1;
                        isLoading=true;
                        new getImage().execute(j);
                        try {
                            arr=new getImage().execute(j).get();
                            arrTotal.addAll(arr);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(MainActivity.this,Detailactivity.class);
        intent.putExtra("data",arrTotal.get(i).getLink());
        intent.putExtra("title",arrTotal.get(i).getTieude());
        startActivity(intent);
    }

    private class getImage extends AsyncTask<Integer,Void,List<Image>>{
        List<Image> lst=new ArrayList<>();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("profile","loading");
        }

        @Override
        protected List<Image> doInBackground(Integer... integers) {
            String urlabs=url+integers[0];
            Log.d("data",""+urlabs);
            try {
                Document doc = Jsoup.connect(urlabs).get();
                Elements elements=doc.select("#wallpapers img");
                for(int i=0;i<elements.size();i++){
                    Element e=elements.get(i);
                    String src=e.attr("src").replace(" ","%20");
                    String tieude=e.attr("alt");
                    Log.d("profile", src+"---"+tieude);
                    lst.add(new Image(tieude,src));
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


            return lst;
        }
        @Override
        protected void onPostExecute(List<Image> aVoid) {
            super.onPostExecute(aVoid);
            isLoading=false;
            Log.d("profile","finished");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
