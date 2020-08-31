package com.tesis.sistemaderecomendacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TableLayout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tesis.spotifyClass.spotifyData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Notification.Listener,Notification {
    private ProgressBar spinner;

    private TabLayout tableLayout;
    private ViewPager viewPager;
    private CallHttp callHttp;
    private TransparentProgressDialog pd;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callHttp=new CallHttp(this);
        context = this;
        pd=  new TransparentProgressDialog(this,R.drawable.loginicon);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        tableLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        tableLayout.setupWithViewPager(viewPager);
        setUpViewPager(viewPager);

    }



    private void setUpViewPager(ViewPager viewPager){
        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());
        tabViewPagerAdapter.addFragment(new Tab1(), "Play list");
        tabViewPagerAdapter.addFragment(new Tab2(), "Sugerido");

        viewPager.setAdapter(tabViewPagerAdapter);
    }

    @Override
    public void register(ID id, Listener listener) {
        if(callHttp!=null){
            callHttp.SetListener(listener);
        }
    }

    @Override
    public void unregister(ID id, Listener listener) {
        if(callHttp!=null){
            callHttp.SetListener(null);
        }
    }

    @Override
    public <T> void post(ID id, T value) {

    }

    @Override
    public void onEvent(ID id, Object value) {
        if(id==ID.GetData){
            RecomendacionVm  resp=(RecomendacionVm) value;
            pd.dismiss();
            MusicaService.getInstance().setCancionInfos(resp);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        register(ID.GetData,this);
        // register(ID.TOKEN,this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregister(ID.GetData,this);
        //unregister(ID.TOKEN,this);
    }
}