package com.tesis.sistemaderecomendacion;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpLogin  extends AsyncTask<String, Void, String> {

    Notification.Listener _listenerlogin;
    Context _context;

    public HttpLogin(Context context){
        _context=context;
    }

    public void  SetListener(Notification.Listener listener){
        _listenerlogin=listener;
    }

    @Override
    protected String doInBackground(String... urls) {
        StringBuilder resul = null;
        int respuesta =0;
        URL url = null;
        String linea = null;

        try{//"https://sistemaderecomendacion.herokuapp.com/grupo/getTestRecomendacion"
            url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();

            resul = new StringBuilder();
            if(respuesta == HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine()) != null){
                    resul.append(linea);
                }
            }
        }catch (Exception ex){
            return  linea;
        }

        return linea;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected void onPostExecute(String linea) {
        if(_listenerlogin!=null){
            _listenerlogin.onEvent(Notification.ID.GetLogin,linea);
        }
        //return result;
    }
}
