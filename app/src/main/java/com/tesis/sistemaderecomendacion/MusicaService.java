package com.tesis.sistemaderecomendacion;


import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tesis.spotifyClass.spotifyData;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class MusicaService {
    private static MusicaService sInstance;

    private RecomendacionVm cancionInfos;
    static Subscriber integerSubscriber ;
    public Subscriber integerSubscriber2 ;

    public void setCancionInfos(final RecomendacionVm cancionInfos) {
        this.cancionInfos = cancionInfos;

      this.integerSubscriber2.onNext(cancionInfos.CancionRecomendada);
    }

    public void setCancionEscuchada(List<CancionInfo> cancionEscuchada) {
        this.cancionEscuchada = cancionEscuchada;
    }

    public void setCancionRecomendada(List<CancionInfo> cancionRecomendada) {
        this.cancionRecomendada = cancionRecomendada;
    }

    private List<CancionInfo> cancionEscuchada = new ArrayList<>();
    private List<CancionInfo> cancionRecomendada = new ArrayList<>();



    private MusicaService() {
        // may be empty
    }

    public static MusicaService getInstance() {
        if (sInstance == null) {
            sInstance = new MusicaService();

            //integerSubscriber
        }
        //sInstance.integerSubscriber2 = integerSubscriber;
        return sInstance;
    }


    public RecomendacionVm getCancionInfos() {
        /*if(cancionInfos == null){
            //cancionInfos = getMusic();
            cancionInfos = getMusic().execute();
        }*/
        return cancionInfos;
    }

    public List<CancionInfo> getCancionEscuchada() {
        ArrayList list = new ArrayList();
        if(cancionInfos != null) list = cancionInfos.CancionEscuchada;
        return list ;
    }

    public List<CancionInfo> getCancionRecomendada() {
        ArrayList list = new ArrayList();
        if(cancionInfos != null) list = cancionInfos.CancionRecomendada;
        return list;
    }

    /*public void setCancionInfos(List<CancionInfo> cancionInfos) {
        this.cancionInfos = cancionInfos;
    }*/
}
