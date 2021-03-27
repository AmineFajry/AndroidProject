package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final private String URL_DATA_JSON = "https://public.opendatasoft.com/api/records/1.0/search/?dataset=sites-2g-3g-4g-france-metropolitaine-mon-reseau-mobile&q=&facet=code_op&facet=nom_op&facet=nom_reg&facet=nom_dep&facet=insee_dep&facet=nom_com&facet=insee_com&facet=technologies";
    private MyAdapter adapter ;
    private ArrayList<Reseau> listReseau = new ArrayList<Reseau>();
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutMainActivity);
        listView = findViewById(R.id.list_view);
        adapter = new MyAdapter(MainActivity.this, listReseau);
        listView.setAdapter(adapter);


        if(isConnectedToInternet(this))
            new HttpsAsyncTask().execute(URL_DATA_JSON,listReseau,adapter);
        else Toast.makeText(this,"You are not connected to the internet",Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isConnectedToInternet(MainActivity.this))
                    new HttpsAsyncTask().execute(URL_DATA_JSON,listReseau,adapter);
                else Toast.makeText(MainActivity.this,"You are not connected to the internet",Toast.LENGTH_SHORT).show();

                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }


    public static boolean isConnectedToInternet(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connMgr != null) {
            NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

            if (activeNetworkInfo != null) { // connected to the internet
                // connected to the mobile provider's data plan
                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    return true;
                } else return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            }
        }
        return false;
    }
}