package com.example.pcc.doctintucrss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    final String vnexpress="https://vnexpress.net/rss/the-gioi.rss";
    ListView lv;
    NewsAdapter adapter;
    ArrayList<NewsModel> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.lv);
        new ReadRSS().execute(vnexpress);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(MainActivity.this,NewsDetailActivity.class);
        intent.putExtra("link",adapter.getItem(i).getLink());
        startActivity(intent);
    }

    class ReadRSS extends AsyncTask<String, Void, ArrayList<NewsModel>>{

        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected ArrayList<NewsModel> doInBackground(String... strings) {
            String url=strings[0];
            try {
                Document doc=Jsoup.connect(url).get();
                Elements elements=doc.select("item");
                for ( Element e:
                     elements) {
                    String title=e.select("title").text();
                    String link=e.select("link").text();
                    String description=e.select("description").text();
                    Document imgurl=Jsoup.parse(description);
                    String urlimage=imgurl.select("img").get(0).attr("src");
                    list.add(new NewsModel(title,urlimage,link));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<NewsModel> newsModels) {
            super.onPostExecute(newsModels);
            dialog.dismiss();
            adapter=new NewsAdapter(MainActivity.this,0,newsModels,getLayoutInflater());
            lv.setAdapter(adapter);
        }
    }
}
