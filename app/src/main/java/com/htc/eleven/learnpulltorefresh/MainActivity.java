package com.htc.eleven.learnpulltorefresh;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView myLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLv = (PullToRefreshListView) findViewById(R.id.myListView);

        List<String> data = new ArrayList<String>();
        data.add("a");
        data.add("b");
        data.add("c");
        data.add("d");

//        myLv.setAdapter(new ArrayAdapter<String >(this,android.R.layout.simple_list_item_1, new String[]{"a","b","c","d"}));

        // constructor with ArrayList.
        final ArrayAdapter adapter = new ArrayAdapter<String >(this,android.R.layout.simple_list_item_1, data);
        myLv.setAdapter(adapter);


        myLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new AsyncTask<Void,Void,Void>(){

                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);

                        adapter.addAll("Hello","eleven");
                        myLv.onRefreshComplete();
                    }
                }.execute();
            }
        });
    }
}
