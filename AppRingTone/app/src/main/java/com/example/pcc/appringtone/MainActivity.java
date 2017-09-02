package com.example.pcc.appringtone;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.pcc.appringtone.Adapter.GridViewAdapter;
import com.example.pcc.appringtone.Model.Image;
import com.example.pcc.appringtone.Model.Mp3;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String url="http://androidwalls.net/";
    List<Image> arr=new ArrayList<>();
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=(GridView)findViewById(R.id.gridView);
        try {
            arr=new getMp3().execute().get();
            Log.d("profile",""+arr);
            GridViewAdapter adapter=new GridViewAdapter(MainActivity.this,R.layout.gridview_item,arr);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(MainActivity.this,Detailactivity.class);
        intent.putExtra("data",arr.get(i).getLink());
        intent.putExtra("title",arr.get(i).getTieude());
        startActivity(intent);
    }

    private class getMp3 extends AsyncTask<Void,Void,List<Image>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("profile","loading");
        }

        @Override
        protected List<Image> doInBackground(Void... voids) {
            List<Image> lst=new ArrayList<>();
            try {
                Document doc = Jsoup.connect(url).get();
                Elements elements=doc.select("img");
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
            Log.d("profile","finished");
        }
    }
}
